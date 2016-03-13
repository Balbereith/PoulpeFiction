package Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ecm.clement.poulpefiction.R;
import com.ecm.clement.poulpefiction.activity.MainNavigationActivity;


public class EventItemFragment extends Fragment {
    private final static String TAG= "FRAGMENT PARAMETERS";
    private OnEventSelectedFragmentInteractionListener mListener;

    public TextView titreView;
    public TextView dateView;
    public TextView realisateurView;
    public TextView participantsView;
    public TextView descriptionView;
    public EventItemFragment() {
        // Required empty public constructor
    }

    public static EventItemFragment newInstance(String param1, String param2) {
        EventItemFragment fragment = new EventItemFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_event_selected, container, false);

        titreView = (TextView) view.findViewById(R.id.ESTitle);
        dateView = (TextView) view.findViewById(R.id.ESDate);
        realisateurView = (TextView) view.findViewById(R.id.ESRealisateur);
        participantsView = (TextView) view.findViewById(R.id.ESActeurs);
        descriptionView = (TextView) view.findViewById(R.id.ESDescription);

        titreView.setText(MainNavigationActivity.eventSelected.getTitre());
        dateView.setText("Du " + MainNavigationActivity.eventSelected.getDate_deb() + " au "+ MainNavigationActivity.eventSelected.getDate_fin() + "\n");
        realisateurView.setText("Contact : " + MainNavigationActivity.eventSelected.getContact() + "\n");
        participantsView.setText("Heure : " + MainNavigationActivity.eventSelected.getHeure() + "\n");
        descriptionView.setText("Synopsis:\n" + MainNavigationActivity.eventSelected.getDescription() + "\n");


        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.OnEventSelectedFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEventSelectedFragmentInteractionListener) {
            mListener = (OnEventSelectedFragmentInteractionListener) context;
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

    public interface OnEventSelectedFragmentInteractionListener {
        void OnEventSelectedFragmentInteraction(Uri uri);
    }
}