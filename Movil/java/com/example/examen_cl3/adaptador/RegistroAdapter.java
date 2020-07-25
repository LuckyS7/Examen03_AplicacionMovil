package com.example.examen_cl3.adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.examen_cl3.R;
import com.example.examen_cl3.entidad.RegistroPedido;

import java.util.List;

public class RegistroAdapter extends ArrayAdapter<RegistroPedido> {

    private Context context;
    private List<RegistroPedido> pedidos;

    public RegistroAdapter(Context context, int resource, List<RegistroPedido> roles) {
        super(context, resource, roles);
        this.context = context;
        this.pedidos = pedidos;
    }

    @Override
    public View getView(final int pos, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_list, parent, false);


        TextView txtId = (TextView) rowView.findViewById(R.id.txtListViewID);
        TextView txtfechaRegistro = (TextView) rowView.findViewById(R.id.txtListViewName);

        txtId.setText(String.format("#ID: %d", pedidos.get(pos).getIdpedido()));
        txtfechaRegistro.setText(String.format("NOMBRE: %s", pedidos.get(pos).getFechaRegistro()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RegistroPedido.class);
                intent.putExtra("var_id", String.valueOf(pedidos.get(pos).getIdcliente()));
                intent.putExtra("var_fechaRegistro", pedidos.get(pos).getFechaRegistro());
                intent.putExtra("var_fechaEntrega", pedidos.get(pos).getFechaEntrega());
                intent.putExtra("var_lugarEntrega", pedidos.get(pos).getLugarEntrega());
                intent.putExtra("var_estado", pedidos.get(pos).getEstado());

                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
