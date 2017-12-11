package adapter;

import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navatejareddy.ratingbarapp.R;

import java.util.List;

import model.RatingAndReview;

/**
 * Created by navatejareddy on 12/10/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<RatingAndReview> ratingAndReviewList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatRatingBar ratingBar;
        public TextView reviewTV;

        public ViewHolder(View v) {
            super(v);
            ratingBar = v.findViewById(R.id.submitted_rating_bar);
            reviewTV = v.findViewById(R.id.submitted_review_tv);

        }
    }

    public RecyclerViewAdapter(List<RatingAndReview> ratingAndReviewsList) {
        this.ratingAndReviewList = ratingAndReviewsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RatingAndReview ratingAndReview = ratingAndReviewList.get(position);
        holder.ratingBar.setRating(ratingAndReview.getRating());
        holder.ratingBar.setIsIndicator(true);
        holder.reviewTV.setText(ratingAndReview.getReview());
    }

    @Override
    public int getItemCount() {
        return ratingAndReviewList.size();
    }
}
