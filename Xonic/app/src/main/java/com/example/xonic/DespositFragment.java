package com.example.xonic;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import io.contentos.android.sdk.encoding.WIF;

import static android.content.Context.CLIPBOARD_SERVICE;
//import static com.example.xonic.MainAccount.privateKey;
//import static com.example.xonic.MainAccount.userName;
import static com.example.xonic.Global.privateKey;
import static com.example.xonic.Global.userName;
import static com.example.xonic.MainAccount.wallet;

public class DespositFragment extends Fragment {
    Button backid;
    TextView publicid;
    ImageView imageqr, copyid;
    Bitmap bitmap;
    ClipboardManager clipboardManager;
    LinearLayout maindes;
    private long mLastClickTime = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_desposit, container, false);
        maindes = (LinearLayout) view.findViewById(R.id.maindes);
        maindes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        backid = (Button) view.findViewById(R.id.backid);
        copyid = (ImageView) view.findViewById(R.id.copyid);
        publicid = (TextView) view.findViewById(R.id.publicid);
        imageqr = (ImageView) view.findViewById(R.id.imageqr);
        String Name = userName;
        String Key = privateKey;
        publicid.setText(Name);
        String publickey  = publicid.getText().toString().trim();
        //create QR code
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = multiFormatWriter.encode(Name, BarcodeFormat.QR_CODE, 250, 250);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageqr.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        backid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //fragmentTransaction.replace(R.id.fragment_container, new DespositWithdrawFragment());
                fragmentTransaction.add(R.id.fragment_container, new DespositWithdrawFragment(), "DWF");
                fragmentTransaction.commit();
            }
        });

        //copy
        clipboardManager = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
        copyid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData clipData = ClipData.newPlainText("PRIVATE", userName);
                clipboardManager.setPrimaryClip(clipData);
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Toast.makeText( getActivity(), "Copy Succes",   Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
