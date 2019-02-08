package tcss450.uw.edu.phishapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import tcss450.uw.edu.phishapp.SetFragment.OnListFragmentInteractionListener;
import tcss450.uw.edu.phishapp.dummy.DummyContent.DummyItem;
import tcss450.uw.edu.phishapp.setlist.SetList;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MySetRecyclerViewAdapter extends RecyclerView.Adapter<MySetRecyclerViewAdapter.ViewHolder> {

    private final List<SetList> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MySetRecyclerViewAdapter(List<SetList> items, OnListFragmentInteractionListener listener) {

        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_set, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mLongDateView.setText(mValues.get(position).getLongDate());
        holder.mLocationView.setText(mValues.get(position).getLocatoin());
        holder.mVenueView.setText(mValues.get(position).getVenue());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mLongDateView;
        public final TextView mLocationView;
        public final TextView mVenueView;
        public SetList mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mLongDateView = (TextView) view.findViewById(R.id.set_longdate);
            mLocationView = (TextView) view.findViewById(R.id.set_location);
            mVenueView = (TextView) view.findViewById(R.id.set_Venue);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mVenueView.getText() + "'";
        }
    }
}
