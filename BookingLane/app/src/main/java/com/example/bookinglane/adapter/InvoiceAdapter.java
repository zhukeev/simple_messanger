package com.example.bookinglane.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookinglane.OnItemClickListener;
import com.example.bookinglane.R;
import com.example.bookinglane.model.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    static List<Invoice> invoices;
    final String url = "https://krista.fashion/uploads/product_images/";
    static Context context;
    final int TYPE_USUAL = 0;
    final int TYPE_RECOMMEND = 1;
    private OnItemClickListener onItemClickListener;


    public InvoiceAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;

        List<Invoice> invoices = new ArrayList<>();

        invoices.add(new Invoice("Company name", "Client name", "15.09.2019", "20:00-20:30",
                "3603 Parker Rd Corpus Christi", "6546 Lone Wolf Trails Green", "SUV", 20, "200$",
                "6546 Lone Wolf Trails Green", "+1 (045) 555 644", "companyname@gmail.com", 98165164));
        invoices.add(new Invoice("Company name", "Client name", "15.09.2019", "20:00-20:30",
                "3603 Parker Rd Corpus Christi", "6546 Lone Wolf Trails Green", "SUV", 20, "200$",
                "6546 Lone Wolf Trails Green", "+1 (045) 555 644", "companyname@gmail.com", 46546466));
        invoices.add(new Invoice("Company name", "Client name", "15.09.2019", "20:00-20:30",
                "3603 Parker Rd Corpus Christi", "6546 Lone Wolf Trails Green", "SUV", 20, "200$",
                "6546 Lone Wolf Trails Green", "+1 (045) 555 644", "companyname@gmail.com", 64646643));
        invoices.add(new Invoice("Company name", "Client name", "15.09.2019", "20:00-20:30",
                "3603 Parker Rd Corpus Christi", "6546 Lone Wolf Trails Green", "SUV", 20, "200$",
                "6546 Lone Wolf Trails Green", "+1 (045) 555 644", "companyname@gmail.com", 646464636));
        invoices.add(new Invoice("Company name", "Client name", "15.09.2019", "20:00-20:30",
                "3603 Parker Rd Corpus Christi", "6546 Lone Wolf Trails Green", "SUV", 20, "200$",
                "6546 Lone Wolf Trails Green", "+1 (045) 555 644", "companyname@gmail.com", 879813161));
        invoices.add(new Invoice("Company name", "Client name", "15.09.2019", "20:00-20:30",
                "3603 Parker Rd Corpus Christi", "6546 Lone Wolf Trails Green", "SUV", 20, "200$",
                "6546 Lone Wolf Trails Green", "+1 (045) 555 644", "companyname@gmail.com", 879813161));
        invoices.add(new Invoice("Company name", "Client name", "15.09.2019", "20:00-20:30",
                "3603 Parker Rd Corpus Christi", "6546 Lone Wolf Trails Green", "SUV", 20, "200$",
                "6546 Lone Wolf Trails Green", "+1 (045) 555 644", "companyname@gmail.com", 879811610));
        invoices.add(new Invoice("Company name", "Client name", "15.09.2019", "20:00-20:30",
                "3603 Parker Rd Corpus Christi", "6546 Lone Wolf Trails Green", "SUV", 20, "200$",
                "6546 Lone Wolf Trails Green", "+1 (045) 555 644", "companyname@gmail.com", 879813160));
        invoices.add(new Invoice("Company name", "Client name", "15.09.2019", "20:00-20:30",
                "3603 Parker Rd Corpus Christi", "6546 Lone Wolf Trails Green", "SUV", 20, "200$",
                "6546 Lone Wolf Trails Green", "+1 (045) 555 644", "companyname@gmail.com", 879813610));
        invoices.add(new Invoice("Company name", "Client name", "15.09.2019", "20:00-20:30",
                "3603 Parker Rd Corpus Christi", "6546 Lone Wolf Trails Green", "SUV", 20, "200$",
                "6546 Lone Wolf Trails Green", "+1 (045) 555 644", "companyname@gmail.com", 879831610));
        invoices.add(new Invoice("Company name", "Client name", "15.09.2019", "20:00-20:30",
                "3603 Parker Rd Corpus Christi", "6546 Lone Wolf Trails Green", "SUV", 20, "200$",
                "6546 Lone Wolf Trails Green", "+1 (045) 555 644", "companyname@gmail.com", 879813160));

        this.invoices = invoices;


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.invoice_list_item, parent, false);


        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(16, 16, 16, 16);
        view.setLayoutParams(lp);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.setData(position);

    }

    @Override
    public int getItemCount() {
        return invoices.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView from_textView;
        TextView to_textView;
        TextView company_textView;
        TextView client_textView;
        TextView date_textView;
        TextView time_textView;
        TextView car_textView;
        TextView mile_textView;
        TextView coast_textView;
        TextView company_address_textView;
        TextView phone_number_textView;
        TextView email_textView;
//        CardView layoutItem;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            from_textView = itemView.findViewById(R.id.from_invoice);
            to_textView = itemView.findViewById(R.id.to_invoice);
            company_textView = itemView.findViewById(R.id.company_invoice);
            client_textView = itemView.findViewById(R.id.client_invoice);
            date_textView = itemView.findViewById(R.id.date_invoice);
            time_textView = itemView.findViewById(R.id.time_invoice);
            car_textView = itemView.findViewById(R.id.car_invoice);
            mile_textView = itemView.findViewById(R.id.miles_invoice);
            coast_textView = itemView.findViewById(R.id.coast_invoice);
            company_address_textView = itemView.findViewById(R.id.company_address_invoice);
            phone_number_textView = itemView.findViewById(R.id.phone_invoice);
            email_textView = itemView.findViewById(R.id.email_invoice);
        }

        void setData(int position) {
            //region Set text and pic
            from_textView.setText("From: " + invoices.get(position).getFrom());
            to_textView.setText("To: " + invoices.get(position).getTo());
            company_textView.setText("Company: " + invoices.get(position).getCompany());
            client_textView.setText("Client: " + invoices.get(position).getClient());
            date_textView.setText("Date: " + invoices.get(position).getDate());
            time_textView.setText("Time: " + invoices.get(position).getTime());
            car_textView.setText("Car: " + invoices.get(position).getCar());
            mile_textView.setText("Miles: " + invoices.get(position).getMiles());
            coast_textView.setText("Coast: " + invoices.get(position).getCoast());
            company_address_textView.setText("Company address: " + invoices.get(position).getCompany_adress());
            phone_number_textView.setText("Phone number: " + invoices.get(position).getPhone_number());
            email_textView.setText("Email: " + invoices.get(position).getEmail());


            //endregion
        }


    }


}
