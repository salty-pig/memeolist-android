package org.jboss.aerogear.memeolist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jboss.aerogear.memeolist.R;

/**
 * Created by summers on 6/28/15.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.Holder> {
        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.comment, parent, false);
            return new Holder(v);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.comment.setText("eiwjoiewjeiwjoiewjeiwjoiewjeiwjoiewjeiwjoiewjeiwjoiewjeiwjoiewjeiwjoiewjeiwjoiewjeiwjoiewjeiwjoiewjeiwjoiewjeiwjoiewjeiwjoiewjeiwjoiewjeiwjoiewjeiwjoiewj");
            holder.creator.setText("secondsun" + position);
        }

        @Override
        public int getItemCount() {
            return 42;
        }




    public static class Holder  extends RecyclerView.ViewHolder  {

        final View layout;
        final TextView creator;
        final TextView comment;

        public Holder(View layout) {
            super(layout);
            this.layout = layout;
            creator = (TextView) layout.findViewById(R.id.creator);
            comment = (TextView) layout.findViewById(R.id.comment);
        }
    }
}
