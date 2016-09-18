package sodaoud.github.io.fyber;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import sodaoud.github.io.fyber.data.FyberResponse;
import sodaoud.github.io.fyber.data.Offer;

import static sodaoud.github.io.fyber.OffersActivity.RESPONSE;

/**
 * A placeholder fragment containing a simple view.
 */
public class OffersActivityFragment extends Fragment {

    private FyberResponse response;
    private RecyclerView list;

    public OffersActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        response = (FyberResponse) getActivity().getIntent().getSerializableExtra(RESPONSE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_offers, container, false);

        list = (RecyclerView) v.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));

        list.setAdapter(new OfferAdapter());

        return v;
    }

    class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferHolder> {

        public class OfferHolder extends RecyclerView.ViewHolder {

            ImageView mThumbnail;
            TextView mTitle;
            TextView mTeaser;
            TextView mPayout;

            public OfferHolder(View itemView) {
                super(itemView);
                mThumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
                mTitle = (TextView) itemView.findViewById(R.id.title);
                mTeaser = (TextView) itemView.findViewById(R.id.teaser);
                mPayout = (TextView) itemView.findViewById(R.id.payout);
            }
        }

        @Override
        public OfferHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.offer_view, parent, false);
            OfferHolder vh = new OfferHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(OfferHolder holder, int position) {
            final Offer offer = response.getOffers()[position];
            holder.mPayout.setText(Integer.toString(offer.getPayout()));
            holder.mTeaser.setText(offer.getTeaser());
            holder.mTitle.setText(offer.getTitle());
            Picasso.with(getActivity()).load(offer.getThumbnail().getHires()).into(holder.mThumbnail);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = offer.getLink();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return response.getOffers().length;
        }
    }
}
