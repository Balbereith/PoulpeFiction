package Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ecm.clement.poulpefiction.R;
import com.ecm.clement.poulpefiction.activity.MainNavigationActivity;
import com.ecm.clement.poulpefiction.models.Film;
import com.ecm.clement.poulpefiction.models.Seance;

import java.util.ArrayList;
import java.util.List;

import adapters.ImageAdapter;
import adapters.VideoAdapter;

public class FilmItemFragment extends Fragment {
        private final static String TAG= "FRAGMENT PARAMETERS";
        private OnFilmSelectedFragmentInteractionListener mListener;

        public TextView mTitreView;
        public TextView mDateView;
        public TextView mRealisateurView;
        public TextView mParticipantsView;
        public TextView mDescriptionView;
        public TextView mListSeanceView;
        public List<String> listImages;
        public String seanceContent;
        public FilmItemFragment() {
            // Required empty public constructor
        }

        public static FilmItemFragment newInstance(String param1, String param2) {
            FilmItemFragment fragment = new FilmItemFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (MainNavigationActivity.filmSelected.getMedias() != null) {
                List<Film.Medias> medias = MainNavigationActivity.filmSelected.getMedias();
                listImages = new ArrayList<String>();
                for(int i=0;i<medias.size();i++) {
                    listImages.add(medias.get(i).getPath());
                }
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view= inflater.inflate(R.layout.fragment_film_selected, container, false);
            HorizontalGridView imageGridView = (HorizontalGridView) view.findViewById(R.id.grid_view_image);
            HorizontalGridView videoGridView = (HorizontalGridView) view.findViewById(R.id.grid_view_video);

            mTitreView = (TextView) view.findViewById(R.id.FSTitle);
            mDateView = (TextView) view.findViewById(R.id.FSDate);
            mRealisateurView = (TextView) view.findViewById(R.id.FSRealisateur);
            mParticipantsView = (TextView) view.findViewById(R.id.FSActeurs);
            mDescriptionView = (TextView) view.findViewById(R.id.FSDescription);
            mListSeanceView = (TextView) view.findViewById(R.id.FSSeanceContent);

            mTitreView.setText(MainNavigationActivity.filmSelected.getTitre());
            mDateView.setText("Sortie le : " + MainNavigationActivity.filmSelected.getDate_sortie() + "\n");
            mRealisateurView.setText("Realisateur : " + MainNavigationActivity.filmSelected.getRealisateur() + "\n");
            mParticipantsView.setText("Figurants : " + MainNavigationActivity.filmSelected.getParticipants() + "\n");
            mDescriptionView.setText("Synopsis:\n" + MainNavigationActivity.filmSelected.getSynopsis() + "\n");

            if(MainNavigationActivity.listSeancesFilmSelected.size() > 0){
                seanceContent="";
                for(int i=0; i<MainNavigationActivity.listSeancesFilmSelected.size();i++){
                    seanceContent += stringifySeance(MainNavigationActivity.listSeancesFilmSelected.get(i)) + "\n";
                }
                mListSeanceView.setText(seanceContent);
            }


            imageGridView.setAdapter(new ImageAdapter(view.getContext()));
            videoGridView.setAdapter(new VideoAdapter(view.getContext()));

            return view;
        }

        public void onButtonPressed(Uri uri) {
            if (mListener != null) {
                mListener.OnFilmSelectedFragmentInteraction(uri);
            }
        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            if (context instanceof OnFilmSelectedFragmentInteractionListener) {
                mListener = (OnFilmSelectedFragmentInteractionListener) context;
            } else {
                throw new RuntimeException(context.toString()
                        + " must implement OnFragmentInteractionListener");
            }
        }

        @Override
        public void onDetach() {
            super.onDetach();
            mListener = null;
        }

        public interface OnFilmSelectedFragmentInteractionListener {
            void OnFilmSelectedFragmentInteraction(Uri uri);
        }

        public String stringifySeance(Seance seance){
            String result="";
            result+=seance.getActual_date() + " - " + seance.getShow_time() + " - " + seance.getCinema_salle() + " - " + seance.getNationality();
            return result;
        }
    }
