package tcss450.uw.edu.phishapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import tcss450.uw.edu.phishapp.blog.BlogPost;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogPostFragment extends Fragment {


    public BlogPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog_post, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();;
        if (getArguments() != null) {
            BlogPost blogPost = (BlogPost) getArguments().get(getString(R.string.blogPost_key));
            updateBlogPostView(blogPost);
        }

        Button button_fragment_blogpost_fullpost = getActivity().findViewById(R.id.button_fragment_blogpost_fullpost);

    }

    public void updateBlogPostView(BlogPost blogPost) {
        TextView textView_fragment_blogpost_title = getActivity().findViewById(R.id.textView_fragment_blogpost_title);
        TextView textView_fragment_blogpost_pubDate = getActivity().findViewById(R.id.textView_fragment_blogpost_pubDate);
        TextView textView_fragment_blogpost_teaser = getActivity().findViewById(R.id.textView_fragment_setpost_Data);
        Button button_fragment_blogpost_fullpost = getActivity().findViewById(R.id.button_fragment_blogpost_fullpost);

        textView_fragment_blogpost_title.setText(blogPost.getTitle());
        textView_fragment_blogpost_pubDate.setText(blogPost.getPubDate());
        textView_fragment_blogpost_teaser.setText(Html.fromHtml(blogPost.getTeaser()));

        button_fragment_blogpost_fullpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(blogPost.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

}
