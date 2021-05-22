package be.bxl.formation.exo_03_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import be.bxl.formation.exo_03_recyclerview.models.Food;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private ArrayList<Food> foods;

    public FoodAdapter(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvFoodName;
        private final CardView cvFoodCategory;
        private final TextView tvFoodCalory;

        public ViewHolder(@NonNull View v) {
            super(v);

            tvFoodName = v.findViewById(R.id.item_food_name);
            cvFoodCategory = v.findViewById(R.id.item_cv_color);
            tvFoodCalory = v.findViewById(R.id.item_food_calory);
        }

        public TextView getTvFoodName() {
            return tvFoodName;
        }

        public CardView getCvFoodCategory() {
            return cvFoodCategory;
        }

        public TextView getTvFoodCalory() {
            return tvFoodCalory;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View foodView = layoutInflater.inflate(R.layout.item_food, parent, false);

        return new ViewHolder(foodView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        Food food = foods.get(position);

        holder.getTvFoodName().setText(food.getName());
        holder.getTvFoodCalory().setText(String.valueOf(food.getCalory()));
        holder.getCvFoodCategory().setBackgroundResource(getCategoryColor(food));
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    private int getCategoryColor(Food food) {
        switch (food.getCategory()) {
            case VEGETABLE:
                return R.color.green;
            case MEAT:
                return R.color.purple_200;
            case FRUIT:
                return R.color.red;
            default:
                return R.color.black;
        }
    }
}
