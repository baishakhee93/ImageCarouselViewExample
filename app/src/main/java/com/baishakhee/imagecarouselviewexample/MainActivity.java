package com.baishakhee.imagecarouselviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baishakhee.imagecarouselviewexample.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselOnScrollListener;
import org.imaginativeworld.whynotimagecarousel.model.CarouselGravity;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.model.CarouselType;
import org.imaginativeworld.whynotimagecarousel.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.relex.circleindicator.CircleIndicator2;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private boolean isStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.carousel.registerLifecycle(getLifecycle());
// Attributes
        binding.carousel.setCarouselPadding(Utils.dpToPx(0, getApplicationContext()));
        binding.carousel.setCarouselPaddingStart(Utils.dpToPx(0, getApplicationContext()));
        binding.carousel.setCarouselPaddingTop(Utils.dpToPx(0, getApplicationContext()));
        binding.carousel.setCarouselPaddingEnd(Utils.dpToPx(0, getApplicationContext()));
        binding.carousel.setCarouselPaddingBottom(Utils.dpToPx(0, getApplicationContext()));

        binding.carousel.setShowTopShadow(true);
        binding.carousel.setTopShadowAlpha(0.6f); // 0 to 1, 1 means 100%
        binding.carousel.setTopShadowHeight(Utils.dpToPx(32, getApplicationContext())); // px value of dp

        binding.carousel.setShowBottomShadow(true);
        binding.carousel.setBottomShadowAlpha(0.6f); // 0 to 1, 1 means 100%
        binding.carousel.setBottomShadowHeight(Utils.dpToPx(64, getApplicationContext())); // px value of dp

        binding.carousel.setShowCaption(true);
        binding.carousel.setCaptionMargin(Utils.dpToPx(0, getApplicationContext())); // px value of dp
        binding.carousel.setCaptionTextSize(Utils.spToPx(14, getApplicationContext())); // px value of sp

        binding.carousel.setShowIndicator(true);
        binding.carousel.setIndicatorMargin(Utils.dpToPx(0, getApplicationContext())); // px value of dp

        binding.carousel.setShowNavigationButtons(true);
        binding.carousel.setImageScaleType(ImageView.ScaleType.CENTER);
        binding.carousel.setCarouselBackground(new ColorDrawable(Color.parseColor("#333333")));
        binding.carousel.setImagePlaceholder(ContextCompat.getDrawable(
                getApplicationContext(),
                R.drawable.gp
        ));


        binding.carousel.setCarouselType(CarouselType.BLOCK);
        binding.carousel.setCarouselGravity(CarouselGravity.CENTER);
        binding.carousel.setScaleOnScroll(true);
        binding.carousel.setScalingFactor(.10f);

        binding.carousel.setAutoWidthFixing(true);

        binding.carousel.setAutoPlay(false);
        binding.carousel.setAutoPlayDelay(3000); // Milliseconds

// Touch to pause autoPlay.
        binding.carousel.setTouchToPause(true);

// Infinite scroll for the carousel.
        binding.carousel.setInfiniteCarousel(true);
        binding.carousel.setOnScrollListener(new CarouselOnScrollListener() {
            @Override
            public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy, int position, @org.jetbrains.annotations.Nullable CarouselItem carouselItem) {
                // ...
            }

            @Override
            public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState, int position, @org.jetbrains.annotations.Nullable CarouselItem carouselItem) {
                // ...
            }
        });

        binding.carousel.setCarouselListener(new CarouselListener() {
            @org.jetbrains.annotations.Nullable
            @Override
            public ViewBinding onCreateViewHolder(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup parent) {
                // ...
                return null;
            }

            @Override
            public void onBindViewHolder(@NotNull ViewBinding binding, @NotNull CarouselItem item, int position) {
                // ...
            }

            @Override
            public void onLongClick(int position, @NotNull CarouselItem carouselItem) {
                // ...
            }

            @Override
            public void onClick(int position, @NotNull CarouselItem carouselItem) {
                // ...
            }
        });

        CircleIndicator2 indicator = binding.customIndicator;
        binding.carousel.setIndicator(indicator);
        MaterialButton previousBtn = binding.btnGotoPrevious;
        previousBtn.setOnClickListener(v -> binding.carousel.previous());

        MaterialButton nextBtn = binding.btnGotoNext;
        nextBtn.setOnClickListener(v -> binding.carousel.next());

        List<CarouselItem> list = new ArrayList<>();

        // Dummy header
        Map<String, String> headers = new HashMap<>();
        headers.put("header_key", "header_value");

        int index = 1;
        for (String item : DataSet.one) {
            list.add(
                    new CarouselItem(
                            item,
                            "Image " + index++ + " of " + DataSet.one.size(),
                            headers
                    )
            );
        }

        binding.carousel.setData(list);
        binding.fabPlay.setOnClickListener(v -> {
            if (isStarted) {

                isStarted = false;
                binding.carousel.stop();

                binding.fabPlay.setText("Start");

            } else {

                isStarted = true;
                binding.carousel.start();

                binding.fabPlay.setText("Stop");

            }
        });
    }
}