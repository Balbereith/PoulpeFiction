package adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.ecm.clement.poulpefiction.R;
import com.ecm.clement.poulpefiction.data.VolleyApplication;
import com.ecm.clement.poulpefiction.models.Event;

import java.util.List;

public class EventViewAdapter extends BaseAdapter{
    List<Event> biblio;
    // LayoutInflater aura pour mission de charger notre fichier XML
    LayoutInflater inflater;


    private class ViewHolder {
        TextView titre;
        TextView soustitre;
        ImageView affiche;
        TextView description;
        TextView partenaires;
        TextView date_deb;
        TextView date_fin;
        TextView heure;
        TextView contact;
        TextView web_label;
        TextView type;
        TextView titre_event;
    }
    public EventViewAdapter(Context context, List<Event> objects) {
        inflater = LayoutInflater.from(context);
        this.biblio = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            Log.v("test", "convertView is null");
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.event_item, null);
            holder.titre = (TextView) convertView.findViewById(R.id.txtTitre);
            holder.soustitre = (TextView) convertView.findViewById(R.id.txtSoustitre);
            holder.affiche = (ImageView) convertView.findViewById(R.id.imgAffiche);
            holder.description = (TextView) convertView.findViewById(R.id.txtdescription);
            holder.partenaires = (TextView) convertView.findViewById(R.id.txtPartenaires);
            holder.date_deb = (TextView) convertView.findViewById(R.id.txtDateDeb);
            holder.date_fin = (TextView) convertView.findViewById(R.id.txtDateFin);
            holder.heure = (TextView) convertView.findViewById(R.id.txtHeure);
            holder.contact = (TextView) convertView.findViewById(R.id.txtContact);
            holder.web_label = (TextView) convertView.findViewById(R.id.txtWebLabel);
            holder.type = (TextView) convertView.findViewById(R.id.txtType);
            holder.titre_event = (TextView) convertView.findViewById(R.id.txtTitreEvent);
            convertView.setTag(holder);
        } else {
            Log.v("test", "convertView is not null");
            holder = (ViewHolder) convertView.getTag();
        }
        Event Event = biblio.get(position);
        holder.titre.setText(Event.getTitre());
        holder.soustitre.setText(Event.getSoustitre());
        this.loadImage(holder, Event.getAffiche());
        holder.description.setText(Event.getDescription());
        holder.partenaires.setText(Event.getPartenaires());
        holder.date_deb.setText(Event.getDate_deb());
        holder.date_fin.setText(Event.getDate_fin());
        holder.heure.setText(Event.getHeure());
        holder.contact.setText(Event.getContact());
        holder.web_label.setText(Event.getWeb_label());
        holder.type.setText(Event.getType());
        holder.titre_event.setText(Event.getTitre_event());
        return convertView;
    }
    /**
     * Retourne le nombre d'éléments
     */
    @Override
    public int getCount() {
// TODO Auto-generated method stub
        return biblio.size();
    }
    /**
     * Retourne l'item à la position
     */
    @Override
    public Event getItem(int position) {
// TODO Auto-generated method stub
        return biblio.get(position);
    }
    /**
     * Retourne la position de l'item
     */
    @Override
    public long getItemId(int position) {
// TODO Auto-generated method stub
        return position;
    }

    public void loadImage(final ViewHolder holder,final String url){
        // Retrieves an image specified by the URL, displays it in the UI.
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(final Bitmap bitmap) {
                                holder.affiche.post(new Runnable() {
                                    public void run() {
                                        holder.affiche.setImageBitmap(bitmap);
                                    }
                                });

                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        holder.affiche.setImageResource(R.drawable.ic_menu_camera); // TODO: add an error image
                    }
                });
// Access the RequestQueue through your singleton class.
        VolleyApplication.getInstance().addToRequestQueue(request);
    }

}
