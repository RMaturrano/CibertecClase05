package pe.cibertec.demo01.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

import pe.cibertec.demo01.R;
import pe.cibertec.demo01.adapter.recyclerview.interfaces.IRVFirstAdapter;
import pe.cibertec.demo01.entities.Persona;

/**
 * Created by Android-SAB-PM on 07/05/2016.
 */
public class RVFirstAdapter extends RecyclerView.Adapter<RVFirstAdapter.RVFirstAdapterViewHolder> {

    private ArrayList<Persona> mLstPersona;
    private IRVFirstAdapter mIRVFirstAdapter;

    public RVFirstAdapter(IRVFirstAdapter mIRVFirstAdapter) {
        this.mIRVFirstAdapter = mIRVFirstAdapter;
        mLstPersona = new ArrayList<>();
    }

    public void add(Persona persona) {
        mLstPersona.add(persona);
        notifyItemInserted(mLstPersona.size() - 1);
    }

    public void addAll(ArrayList<Persona> lstPersona) {
        int position = mLstPersona.size();
        mLstPersona.addAll(lstPersona);
        notifyItemRangeInserted(position, lstPersona.size());
    }

    public void clearAndAddAll(ArrayList<Persona> lstPersona) {
        mLstPersona.clear();
        mLstPersona.addAll(lstPersona);
        notifyDataSetChanged();
    }

    @Override
    public RVFirstAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVFirstAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.first_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RVFirstAdapterViewHolder holder, int position) {
        Persona persona = mLstPersona.get(position);
        String codigo = "00000000" + String.valueOf(persona.getPersonaId());
        holder.tvFirstItemCodigo.setText(codigo.substring(String.valueOf(persona.getPersonaId()).length()));
        holder.tvFirstItemNombreCompleto.setText(persona.getNombre() + " " + persona.getApellido());
        holder.tvFirstItemEdad.setText(String.valueOf(persona.getEdad()));
        holder.tvFirstItemDNI.setText(persona.getDni());
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(itemViewOnClickListener);
    }

    View.OnClickListener itemViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            mIRVFirstAdapter.onSelectItem(mLstPersona.get(position));
        }
    };

    @Override
    public int getItemCount() {
        return mLstPersona.size();
    }

    class RVFirstAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView tvFirstItemCodigo, tvFirstItemNombreCompleto, tvFirstItemDNI, tvFirstItemEdad;

        public RVFirstAdapterViewHolder(View itemView) {
            super(itemView);
            tvFirstItemCodigo = (TextView) itemView.findViewById(R.id.tvFirstItemCodigo);
            tvFirstItemNombreCompleto = (TextView) itemView.findViewById(R.id.tvFirstItemNombreCompleto);
            tvFirstItemDNI = (TextView) itemView.findViewById(R.id.tvFirstItemDNI);
            tvFirstItemEdad = (TextView) itemView.findViewById(R.id.tvFirstItemEdad);
        }
    }
}
