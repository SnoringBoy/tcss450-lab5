package tcss450.uw.edu.phishapp;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tcss450.uw.edu.phishapp.BlogFragment.OnListFragmentInteractionListener;
import tcss450.uw.edu.phishapp.blog.BlogGenerator;
import tcss450.uw.edu.phishapp.blog.BlogPost;

import java.util.List;


/**
 * {@link RecyclerView.Adapter} that can display a {@link tcss450.uw.edu.phishapp.blog.BlogPost} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyBlogRecyclerViewAdapter extends RecyclerView.Adapter<MyBlogRecyclerViewAdapter.ViewHolder> {

    private final List<BlogPost> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyBlogRecyclerViewAdapter(List<BlogPost> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_blog, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mBlogTitleView.setText(mValues.get(position).getTitle());
        holder.mPubDate.setText(mValues.get(position).getPubDate());
        holder.mTeaserView.setText(Html.fromHtml(mValues.get(position).getTeaser()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {

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
        public final TextView mBlogTitleView;
        public final TextView mPubDate;
        public final TextView mTeaserView;
        public BlogPost mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mBlogTitleView = (TextView) view.findViewById(R.id.blog_title);
            mPubDate = (TextView) view.findViewById(R.id.publish_date);
            mTeaserView = (TextView) view.findViewById(R.id.blog_teaser);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTeaserView.getText() + "'";
        }
    }
}
