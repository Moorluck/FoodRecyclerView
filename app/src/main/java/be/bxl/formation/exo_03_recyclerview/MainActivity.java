package be.bxl.formation.exo_03_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import be.bxl.formation.exo_03_recyclerview.models.Food;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Food> foods;

    private EditText etName, etCalory;
    private Spinner spCategory;
    private Button btnAdd;
    private RecyclerView rvFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation de la liste
        foods = new ArrayList<>();

        // Liaison avec le layoute
        etName = findViewById(R.id.et_main_food_name);
        etCalory = findViewById(R.id.et_main_food_calory);
        spCategory = findViewById(R.id.sp_main_food_type);
        btnAdd = findViewById(R.id.btn_main_add_food);
        rvFoods = findViewById(R.id.rv_main_foods);

        // Définition du Spinner
        List<String> categoryChoices = new ArrayList<>();
        categoryChoices.add(getString(R.string.choice_category_vegetable));
        categoryChoices.add(getString(R.string.choice_category_meat));
        categoryChoices.add(getString(R.string.choice_category_fruit));

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_item,
                android.R.id.text1,
                categoryChoices
        );

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spCategory.setAdapter(spinnerAdapter);

        rvFoods.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        FoodAdapter foodAdapter = new FoodAdapter(foods);
        rvFoods.setAdapter(foodAdapter);

        btnAdd.setOnClickListener(v -> {
            if (etName.getText().toString().equals("")) {
                Toast.makeText(this, "Entrez un nom d'aliment !", Toast.LENGTH_SHORT).show();
            }
            else if (etCalory.getText().toString().equals("")){
                Toast.makeText(this, "Entrez un nombre de calorie !", Toast.LENGTH_SHORT).show();
            }
            else {
                String name = etName.getText().toString();
                double calory = Double.parseDouble(etCalory.getText().toString());
                Food.Category category = getCategorySelected();

                foods.add(new Food(name, calory, category));
                foodAdapter.notifyDataSetChanged();
            }
        });

    }

    private Food.Category getCategorySelected() {

        String vegetableString = getString(R.string.choice_category_vegetable);
        String meatString = getString(R.string.choice_category_meat);
        String fruitString = getString(R.string.choice_category_fruit);

        String categorySelectedString = spCategory.getSelectedItem().toString();

        if (categorySelectedString.equals(vegetableString)) {
            return Food.Category.VEGETABLE;
        }

        else if (categorySelectedString.equals(meatString)){
            return Food.Category.MEAT;
        }

        else if (categorySelectedString.equals(fruitString)){
            return Food.Category.FRUIT;
        }
        else {
            throw new RuntimeException("Pas de catégorie sélectionnée");
        }

    }
}