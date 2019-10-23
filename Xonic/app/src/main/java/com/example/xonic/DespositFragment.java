package com.example.xonic;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import static com.example.xonic.MainAccount.privateKey;
import static com.example.xonic.MainAccount.userName;
import static com.example.xonic.MainAccount.wallet;

public class DespositFragment extends Fragment {
    Button backid,copyid;
    TextView publicid;
    ImageView imageqr;
    Bitmap bitmap;
    ClipboardManager clipboardManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_desposit, container, false);
        backid = (Button) view.findViewById(R.id.backid);
        copyid = (Button) view.findViewById(R.id.copyid);
        publicid = (TextView) view.findViewById(R.id.publicid);
        imageqr = (ImageView) view.findViewById(R.id.imageqr);
        String Name = userName;
        String Key = privateKey;
        //String publicKey = WIF.fromPublicKey(wallet.getAccountByName(userName).getInfo().getPublicKey());
        publicid.setText(Name);
        String publickey  = publicid.getText().toString().trim();
        //create QR code
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = multiFormatWriter.encode(Name, BarcodeFormat.QR_CODE, 200, 200);
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
                //DespositW walletFragment = new WalletFragment();
                fragmentTransaction.replace(R.id.fragment_container, new DespositWithdrawFragment());
//                //fragmentTransaction.addToBackStack("Desposit and Withdraw");
                fragmentTransaction.commit();
            }
        });

        //copy
        clipboardManager = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
        copyid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String public1 =  publicid.getText().toString();
                ClipData clipData = ClipData.newPlainText("PRIVATE", userName);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText( getActivity(), "Copy Succes",   Toast.LENGTH_SHORT).show();
            }
        });
        // Bitmap bm = encodeAsBitmap(barcode_content, BarcodeFormat.QR_CODE, 150, 150);
        //bitmap = TextToImageEncode(etqr.getText().toString());
        return view;
    }
}