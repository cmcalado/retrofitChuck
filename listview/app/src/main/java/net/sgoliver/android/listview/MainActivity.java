package net.sgoliver.android.listview;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private TextView lblEtiqueta;
    private ListView lstOpciones;

    private Alumno[] datos =
            new Alumno[]{
                    new Alumno("Calado", "Carlos", "2º", "DAM", "Masculino"),
                    new Alumno("Garcia", "Dani", "2º", "DAM", "Masculino"),
                    new Alumno("Benavides", "Alex", "2º", "DAM", "Masculino"),
                    new Alumno("Contreras", "Alejandro", "2º", "DAM", "Masculino"),
                    new Alumno("Nuez", "Guillermo", "2º", "DAM", "Masculino"),
                    new Alumno("Garrido", "Alejandro", "2º", "DAM", "Masculino"),
                    new Alumno("Gallardo", "Jaime", "2º", "DAM", "Masculino"),
                    new Alumno("Prados", "David", "2º", "DAM", "Masculino"),
                    new Alumno("Ramos", "Maikel", "2º", "DAM", "Masculino"),
                    new Alumno("Apellidos", "Nombre", "Clase", "Nivel", "Sexo"),
                    new Alumno("Apellidos", "Nombre", "Clase", "Nivel", "Sexo"),
                    new Alumno("Apellidos", "Nombre", "Clase", "Nivel", "Sexo"),
                    new Alumno("Apellidos", "Nombre", "Clase", "Nivel", "Sexo"),
                    new Alumno("Apellidos", "Nombre", "Clase", "Nivel", "Sexo"),
                    new Alumno("Apellidos", "Nombre", "Clase", "Nivel", "Sexo")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ejemplo básico
        //final String[] datos =
        //        new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};
        //
        //ArrayAdapter<String> adaptador =
        //        new ArrayAdapter<String>(this,
        //                android.R.layout.simple_list_item_1, datos);
        //
        //lstOpciones = (ListView)findViewById(R.id.LstOpciones);
        //
        //lstOpciones.setAdapter(adaptador);

        lblEtiqueta = (TextView)findViewById(R.id.LblEtiqueta);
        lstOpciones = (ListView)findViewById(R.id.LstOpciones);

        //Cabecera
        View header = getLayoutInflater().inflate(R.layout.list_header, null);
        lstOpciones.addHeaderView(header);

        //Adaptador
        AdaptadorTitulares adaptador =
                new AdaptadorTitulares(this, datos);

        lstOpciones.setAdapter(adaptador);

        //Eventos
        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                //Alternativa 1:
                String opcionSeleccionada =
                        ((Alumno)a.getItemAtPosition(position)).getNombre();

                //Alternativa 2:
                //String opcionSeleccionada =
                //		((TextView)v.findViewById(R.id.LblTitulo))
                //			.getText().toString();

                lblEtiqueta.setText("Opción seleccionada: " + opcionSeleccionada);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class AdaptadorTitulares extends ArrayAdapter<Alumno> {

        public AdaptadorTitulares(Context context, Alumno[] datos) {
            super(context, R.layout.listitem_alumno, datos);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            View item = convertView;
            ViewHolder holder;

            if (item == null) {

                LayoutInflater inflater = LayoutInflater.from(getContext());
                item = inflater.inflate(R.layout.listitem_alumno, null);

                holder = new ViewHolder();
                holder.Nombre = (TextView)findViewById(R.id.LblNombre);
                holder.Clase = (TextView)findViewById(R.id.LblClase);

                item.setTag(holder);
            }
            else {
                holder = (ViewHolder)item.getTag();
            }

            holder.Nombre.setText(datos[position].getApellidos()+" , "+datos[position].getNombre());
            holder.Clase.setText(datos[position].getClase()+" - "+datos[position].getNivel());

            ImageView lblIcono = (ImageView)item.findViewById(R.id.lblIcono);
            if (datos[position].getSexo() == "Masculino"){
               // lblIcono.setImageResource(R.drawable.tio);
            }
            if (datos[position].getSexo() == "Femenino") {
               // lblIcono.setImageResource(R.drawable.tia);
            }


            // Sin viewHolder
            /*TextView lblTitulo = (TextView)item.findViewById(R.id.LblNombres);
            lblTitulo.setText(datos[position].getApellidos()+" , "+datos[position].getNombre());

            TextView lblSubtitulo = (TextView)item.findViewById(R.id.LblClase);
            lblSubtitulo.setText(datos[position].getClase()+" - "+datos[position].getNivel());*/

            return(item);
        }
    }

    static class ViewHolder {
        TextView Nombre;
        TextView Apellidos;
        TextView Clase;
        TextView Nivel;
    }
}
