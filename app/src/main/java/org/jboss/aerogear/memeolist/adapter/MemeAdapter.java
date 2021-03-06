package org.jboss.aerogear.memeolist.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jboss.aerogear.memeolist.R;
import org.jboss.aerogear.memeolist.content.vo.Post;
import org.jboss.aerogear.memeolist.utils.KeycloakEnabledPicasso;
import org.jboss.aerogear.memeolist.utils.UIUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by summers on 6/18/15.
 */
public class MemeAdapter extends RecyclerView.Adapter<MemeAdapter.ViewHolder> {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private final List<Post> posts;
    private final Context appContext;
    private final Picasso picasso;
    private CardOnClickHandler feedbackOnClickHandler;
    private CardOnClickHandler favoriteOnClickHandler;
    private CardOnClickHandler authorOnClickHandler;

    public MemeAdapter(Context context) {
        this.appContext = context.getApplicationContext();
        posts = new ArrayList<>();
        picasso = KeycloakEnabledPicasso.getPicasso(appContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meme_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Post post = posts.get(position);
        holder.favoriteCount.setText("");
        holder.feedbackCount.setText("");

        try {
            picasso
                    .load(post.getFileUrl().toString())
                    .into(holder.memePhoto);

        } catch (Exception e) {
            throw new RuntimeException((e));
        }
        holder.postedDate.setText(FORMAT.format(post.getPosted()));
        UIUtils.setTextWithUnderline(holder.creator, "secondsun");

        holder.feedbackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (feedbackOnClickHandler != null) {
                    feedbackOnClickHandler.onCardClick(post, holder);
                }
            }
        });

        holder.favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favoriteOnClickHandler!= null) {
                    favoriteOnClickHandler.onCardClick(post, holder);
                }
            }
        });

        holder.creator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (authorOnClickHandler!= null) {
                    authorOnClickHandler.onCardClick(holder.creator.getText().toString(), holder);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView postedDate;
        public final ImageView memePhoto;
        public final TextView creator;
        final TextView feedbackCount;
        final TextView favoriteCount;
        public final ImageView feedbackIcon;
        public final ImageView favoriteIcon;
        public final CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);
            View view = itemView;
            postedDate = (TextView) view.findViewById(R.id.posted_date);
            memePhoto = (ImageView) view.findViewById(R.id.meme_photo);
            feedbackCount = (TextView) view.findViewById(R.id.feedback_count);
            favoriteCount = (TextView) view.findViewById(R.id.favorite_count);
            feedbackIcon = (ImageView) view.findViewById(R.id.feedback_icon);
            favoriteIcon = (ImageView) view.findViewById(R.id.favorite_icon);
            creator = (TextView) view.findViewById(R.id.creator);
            cardView= (CardView) view.findViewById(R.id.card_view);

        }


    }

    public CardOnClickHandler getFeedbackOnClickHandler() {
        return feedbackOnClickHandler;
    }

    public void setFeedbackOnClickHandler(CardOnClickHandler feedbackOnClickHandler) {
        this.feedbackOnClickHandler = feedbackOnClickHandler;
    }

    public CardOnClickHandler getFavoriteOnClickHandler() {
        return favoriteOnClickHandler;
    }

    public void setFavoriteOnClickHandler(CardOnClickHandler favoriteOnClickHandler) {
        this.favoriteOnClickHandler = favoriteOnClickHandler;
    }

    public CardOnClickHandler getAuthorOnClickHandler() {
        return authorOnClickHandler;
    }

    public void setAuthorOnClickHandler(CardOnClickHandler authorOnClickHandler) {
        this.authorOnClickHandler = authorOnClickHandler;
    }

    public void setPosts(List<Post> newPosts) {
        posts.clear();
        posts.addAll(newPosts);
        this.notifyDataSetChanged();
    }
}
