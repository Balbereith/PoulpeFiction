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
import com.ecm.clement.poulpefiction.models.Film;

import java.util.List;


public class FilmViewAdapter extends BaseAdapter {
    List<Film> biblio;
    // LayoutInflater aura pour mission de charger notre fichier XML
    LayoutInflater inflater;
    /**
     * Elle nous servira à mémoriser les éléments de la liste en mémoire pour
     * qu’à chaque rafraichissement l’écran ne scintille pas
     *
     * @author patrice
     */
    private class ViewHolder {
        TextView titreView;
        TextView dateView;
        TextView genreView;
        ImageView afficheView;
    }
    public FilmViewAdapter(Context context, List<Film> objects) {
        inflater = LayoutInflater.from(context);
        this.biblio = objects;
    }
    /**
     * Génère la vue pour un objet
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            Log.v("test", "convertView is null");
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.film_item, null);
            holder.titreView = (TextView) convertView.findViewById(R.id.titreFilm);
            holder.dateView = (TextView) convertView
                    .findViewById(R.id.dateFilm);
            holder.genreView = (TextView) convertView.findViewById(R.id.genreFilm);
            holder.afficheView = (ImageView) convertView.findViewById(R.id.affiche);

            convertView.setTag(holder);
        } else {
            Log.v("test", "convertView is not null");
            holder = (ViewHolder) convertView.getTag();
        }
        Film film = biblio.get(position);
        holder.titreView.setText(film.getTitre());
        holder.genreView.setText(film.getGenre());
        holder.dateView.setText(film.getDate_sortie());
        loadImageFromUrl(holder, film.getAffiche());
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
    public Film getItem(int position) {
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

    public void loadImageFromUrl(final ViewHolder holder,final String url){
        // Retrieves an image specified by the URL, displays it in the UI.
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(final Bitmap bitmap) {
                                holder.afficheView.post(new Runnable() {
                                    public void run() {
                                        holder.afficheView.setImageBitmap(bitmap);
                                    }
                                });

                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        holder.afficheView.setImageResource(R.drawable.a15c6213); // TODO: add an error image
                    }
                });
// Access the RequestQueue through your singleton class.
        VolleyApplication.getInstance().addToRequestQueue(request);
    }
}
