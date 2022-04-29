package com.example.contact;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Dialog extends AppCompatDialogFragment implements View.OnClickListener{

    TextView full_name, phone_number;
    Button call, sms;

    private String name;
    private String phone;
    public Dialog(String name,String phone) {
        this.name = name;
        this.phone = phone;
    }
    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        builder.setView(view)
                .setTitle("Contact")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        full_name = (TextView) view.findViewById(R.id.full_name);
        phone_number = (TextView) view.findViewById(R.id.phone_number);
        full_name.setText(name);
        phone_number.setText(phone);
        call = (Button) view.findViewById(R.id.call);
        call.setOnClickListener(this);
        sms = (Button) view.findViewById(R.id.sms);
        sms.setOnClickListener(this);
        return builder.create();

    }
    public void onClick(View view) {
        if(view == call){
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
            startActivity(intent);
        }
        if(view == sms){
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phone));
            startActivity(intent);
        }
    }
}
