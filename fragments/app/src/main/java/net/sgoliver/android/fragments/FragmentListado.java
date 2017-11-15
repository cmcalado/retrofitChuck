package net.sgoliver.android.fragments;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentListado extends Fragment {
	
	private Artista[] datos =
	    	new Artista[]{
	    		new Artista("Five Finger Death Punch", "descripcion"),
	    		new Artista("Avenged Sevenfold", "descirpcion"),
	    		new Artista("Linkin Park", "descripcion"),
	    		new Artista("Disturbed", "descirpcion"),
	    		new Artista("Slipknot", "descripcion")};
	
	private ListView lstListado;
	
	private CorreosListener listener;
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
			                 ViewGroup container, 
			                 Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_listado, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);
		
		lstListado = (ListView)getView().findViewById(R.id.LstListado);
		
		lstListado.setAdapter(new AdaptadorCorreos(this));
		
		lstListado.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
				if (listener!=null) {
					listener.onCorreoSeleccionado(
							(Artista)lstListado.getAdapter().getItem(pos));
				}
			}
		});
	}
	
	class AdaptadorCorreos extends ArrayAdapter<Artista> {
    	
    	Activity context;
    	
    	AdaptadorCorreos(Fragment context) {
    		super(context.getActivity(), R.layout.listitem_correo, datos);
    		this.context = context.getActivity();
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.listitem_correo, null);
			
			TextView lblDe = (TextView)item.findViewById(R.id.LblNombre);
			lblDe.setText(datos[position].getNombre());

			ImageView ImgLogo = (ImageView) item.findViewById(R.id.ImgFoto);
			if (datos[position].getNombre() == "Five Finger Death Punch"){
				ImgLogo.setImageResource(R.mipmap.five_finger);
			}
			if (datos[position].getNombre() == "Avenged Sevenfold") {
				ImgLogo.setImageResource(R.mipmap.avenged_sevenfold);
			}
			if (datos[position].getNombre() == "Linkin Park") {
				ImgLogo.setImageResource(R.mipmap.linkin_park);
			}
			if (datos[position].getNombre() == "Disturbed") {
				ImgLogo.setImageResource(R.mipmap.disturbed);
			}
			if (datos[position].getNombre() == "Slipknot") {
				ImgLogo.setImageResource(R.mipmap.slipknot);
			}
			
			return(item);
		}
    }
	
	public interface CorreosListener {
		void onCorreoSeleccionado(Artista c);
	}
	
	public void setCorreosListener(CorreosListener listener) {
		this.listener=listener;
	}
}
