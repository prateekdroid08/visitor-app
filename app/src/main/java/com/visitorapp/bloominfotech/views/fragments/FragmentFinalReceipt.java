package com.visitorapp.bloominfotech.views.fragments;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.visitorapp.bloominfotech.R;
import com.visitorapp.bloominfotech.adapters.CompanyAdapter;
import com.visitorapp.bloominfotech.adapters.ReceiptAdapter;
import com.visitorapp.bloominfotech.interfaces.OnReceiptItemClickListener;
import com.visitorapp.bloominfotech.models.FinalReceiptModel;
import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;
import com.visitorapp.bloominfotech.models.form_response.ResponseVisitorForm;
import com.visitorapp.bloominfotech.presenter.companies.CompanylistPresenter;
import com.visitorapp.bloominfotech.presenter.final_receipt.ReceiptPresenter;
import com.visitorapp.bloominfotech.presenter.final_receipt.ReceiptPresenterImpl;
import com.visitorapp.bloominfotech.presenter.final_receipt.ReceiptView;
import com.visitorapp.bloominfotech.utils.PermissionUtil;
import com.visitorapp.bloominfotech.utils.ProgressDialog;
import com.visitorapp.bloominfotech.utils.SpacesItemDecoration;
import com.visitorapp.bloominfotech.utils.ViewUtils;
import com.visitorapp.bloominfotech.views.activity.HomeActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * Created by hp on 11/7/2016.
 */

public class FragmentFinalReceipt extends Fragment implements ReceiptView, OnReceiptItemClickListener {

    View view;


    @Bind(R.id.home_recycler_view)
    RecyclerView recyclerView;


    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.progressBar)
    CircleProgressBar progress;

    @Bind(R.id.main__container)
    CoordinatorLayout mCoordinate;


    @Bind(R.id.error_message)
    TextView error_message;
    ResponseVisitorForm responseVisitorForm = new ResponseVisitorForm();
    boolean IsSwipeRefreshLayoutActive = false;
    LinearLayoutManager mLayoutManager;
    ReceiptPresenter receiptPresenter;
    ArrayList<FinalReceiptModel> rowItems = new ArrayList<FinalReceiptModel>();

    ProgressDialog progressDialog;

    /*Permission picture camera*/
    final String[] permissionsFile = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int PERMISSIONS_REQUEST_FILE_SAVE = 110;

    private static String FILE = "mnt/sdcard/Receipt.pdf";

    static Image image;
    static byte[] bArray;

    LinearLayout llView;
    LinearLayout llSubView;
    TextView txtView;


    public static FragmentFinalReceipt newInstance() {
        FragmentFinalReceipt fragmentFinalReceipt = new FragmentFinalReceipt();
        return fragmentFinalReceipt;
    }

    ReceiptAdapter mAdapter;
    String message = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_final_receipt, container, false);
 /*init butterknife*/
        ButterKnife.bind(this, view);

        ((HomeActivity) getActivity()).toolbar.setVisibility(View.GONE);
        ((HomeActivity) getActivity()).mToolbarTitle.setText("");
        receiptPresenter = new ReceiptPresenterImpl(getActivity(), this);
   /*recycler view properties*/
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());


        Bundle bundle = this.getArguments();

        if (bundle != null) {
            message = bundle.getString("message", "");

        }


        recyclerView.setLayoutManager(mLayoutManager);
        // 3. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        } else {
            recyclerView.addItemDecoration(new SpacesItemDecoration(0));
        }

        recyclerView.setHasFixedSize(true);

        /*Swipe refresh layout*/
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                IsSwipeRefreshLayoutActive = true;
                getvalueFromserver();
            }


        });


        getvalueFromserver();

        return view;
    }

    private void getvalueFromserver() {

        receiptPresenter.getFinalreceipt(message);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void onSuccess(ResponseVisitorForm responseVisitorForm) {
        if (mSwipeRefreshLayout != null) {
            if (mSwipeRefreshLayout.isRefreshing() == true) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }

        if (responseVisitorForm != null) {


            if (rowItems.size() > 0) {
                rowItems.clear();
            }

            String nameStr = responseVisitorForm.getUserDetails().getFirstName() + " " +
                    (responseVisitorForm.getUserDetails().getLastName() == null ? "" :
                            responseVisitorForm.getUserDetails().getLastName());
            String companuStr = responseVisitorForm.getCompanyDetails().getCompanyName();
            String purposestr = responseVisitorForm.getPurposeDetails().getPurposeName();
            String uniqueKey = responseVisitorForm.getUniqueKey();
            String uniqueKeylocation = responseVisitorForm.getUniqueKeyLocation();
            String createdOn = responseVisitorForm.getCreatedOn();

            String dateAsString = "";
            String timeAsString = "";

            String ImageUrl = "http://www.externalbloominfotechinc.com/UniqueKeys/" + uniqueKeylocation + ".jpg";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date date = null;
            try {
                date = dateFormat.parse(createdOn);

                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");

                dateAsString = dateFormatter.format(date);
                timeAsString = timeFormatter.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            FinalReceiptModel item = new FinalReceiptModel(nameStr, companuStr, purposestr, uniqueKey, dateAsString, timeAsString, ImageUrl);
            rowItems.add(item);


            if (!responseVisitorForm.getUserMembers().equalsIgnoreCase("")) {
                String otherMembers = responseVisitorForm.getUserMembers();
                List<String> items = Arrays.asList(otherMembers.split("\\s*,\\s*"));

                for (int i = 0; i < items.size(); i++) {
                    item = new FinalReceiptModel(items.get(i), companuStr, purposestr, uniqueKey, dateAsString, timeAsString, ImageUrl);
                    rowItems.add(item);
                }

            }

            System.out.println("size : " + rowItems.size());
  /*setting adapter*/

            if (rowItems.size() > 0) {
                mAdapter = new ReceiptAdapter(getActivity(), rowItems, this);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onError(String message) {
        ViewUtils.showMessage(getActivity(), message);
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void onReceiptViewItemSelected(ArrayList<FinalReceiptModel> rowItems, int position, LinearLayout ll, TextView tv, LinearLayout SubView) {
        llView = ll;
        txtView = tv;
        llSubView = SubView;
        getpermissionFILERequired();
    }

    public void getpermissionFILERequired() {


        // Verify that all required contact permissions have been granted.
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Contacts permissions have not been granted.

            requestCameraPicturePermissions();

        } else {
            SaveImageToPhone();
        }


    }


    private void requestCameraPicturePermissions() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            Snackbar.make(mCoordinate, R.string.permission_file_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat
                                    .requestPermissions(getActivity(), permissionsFile,
                                            PERMISSIONS_REQUEST_FILE_SAVE);
                        }
                    })
                    .show();
        } else {
            // Contact permissions have not been granted yet. Request them directly.
            ActivityCompat.requestPermissions(getActivity(), permissionsFile, PERMISSIONS_REQUEST_FILE_SAVE);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_FILE_SAVE: {

                if (PermissionUtil.verifyPermissions(grantResults)) {
                    // All required permissions have been granted, display contacts fragment.
                    Snackbar.make(mCoordinate, R.string.permision_available_file,
                            Snackbar.LENGTH_SHORT)
                            .show();

                    SaveImageToPhone();


                } else {

                    Snackbar.make(mCoordinate, R.string.permissions_not_granted,
                            Snackbar.LENGTH_SHORT)
                            .show();


                }


                return;
            }
        }
    }

    private void SaveImageToPhone() {
/*hide view pdf button*/
        txtView.setVisibility(View.GONE);
        llSubView.setBackgroundResource(R.drawable.card_like_background);

        llView.setDrawingCacheEnabled(true);
        Bitmap screen = getBitmapFromView(llView);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        screen.compress(Bitmap.CompressFormat.PNG, 80, stream);
        bArray = stream.toByteArray();

        llView.setDrawingCacheEnabled(false);

        try {
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();

            addImage(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtView.setVisibility(View.VISIBLE);
        llSubView.setBackgroundResource(R.drawable.custom_receipt_background);
        File fileuri = new File(FILE);
        if (fileuri != null) {
            sentFileForPrint(fileuri);
        } else {
            System.out.println("pdf not created yet.");
        }


    }

    private void sentFileForPrint(File filedummy) {

        Uri filepath = Uri.fromFile(filedummy);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // intent.setPackage("com.adobe.reader");
        intent.setDataAndType(filepath, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // No application to view, ask to download one
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("No Application Found");
            builder.setMessage("Download one from Android Market?");
            builder.setPositiveButton("Yes, Please",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent marketIntent = new Intent(Intent.ACTION_VIEW);
                            marketIntent
                                    .setData(Uri
                                            .parse("market://details?id=com.adobe.reader"));
                            startActivity(marketIntent);
                        }
                    });
            builder.setNegativeButton("No, Thanks", null);
            builder.create().show();
        }

    }


    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);


        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    private static void addImage(Document document) {

        try {
            image = Image.getInstance(bArray); ///Here i set byte array..you can do bitmap to byte array and set in image...
        } catch (BadElementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // image.scaleAbsolute(150f, 150f);
        try {
            document.add(image);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}


