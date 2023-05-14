package App;
import Other.AddFlatPanel;
import Other.Flat;
import Other.SearchPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class FlatSearchApp extends JFrame {
    private JPanel cards; // панель, содержащая две панели с разными функциями
    private SearchPanel searchPanel; // панель для поиска квартир
    private AddFlatPanel addFlatPanel; // панель для добавления квартиры

    private static final List<Flat> flatsList = new ArrayList<>(); // список квартир

    public FlatSearchApp() {
        super("Продажа и поиск квартир");

        // Создаем две панели с разными функциями
        searchPanel = new SearchPanel();
        addFlatPanel = new AddFlatPanel();

        // Создаем панель-контейнер, содержащую две панели с разными функциями
        cards = new JPanel(new CardLayout());
        cards.add(searchPanel, "Поиск");
        cards.add(addFlatPanel, "Добавить");

        // Создаем кнопки для переключения между панелями
        JButton searchButton = new JButton("Поиск");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "Поиск");
            }
        });

        JButton addButton = new JButton("Добавить квартиру");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "Добавить");
            }
        });

        // Создаем панель для кнопок переключения между панелями
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(searchButton);
        buttonPanel.add(addButton);

        // Добавляем панель с кнопками и панель-контейнер на главную панель окна
        add(buttonPanel, BorderLayout.NORTH);
        add(cards, BorderLayout.CENTER);

        // Настраиваем окно
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        try (BufferedReader br = new BufferedReader(new FileReader("flats.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                String district = parts[0];
                String buildingCategory = parts[1];
                int buildingFloors = Integer.parseInt(parts[2]);
                int floor = Integer.parseInt(parts[3]);
                double totalArea = Double.parseDouble(parts[4].replaceAll(",", "."));
                double livingArea = Double.parseDouble(parts[5].replaceAll(",", "."));
                int rooms = Integer.parseInt(parts[6]);
                String address = parts[7];

                String priceStr = parts[8].replaceAll(",", ".");
                int price = 0;
                if (priceStr.matches("[0-9,.]+")) {
                    price = (int) Double.parseDouble(priceStr);
                }

                Flat flat = new Flat(district, buildingCategory, buildingFloors, floor, totalArea, livingArea, rooms, address, price);
                flatsList.add(flat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Flat> getFlatsList(){
        return flatsList;
    }

    public static void main(String[] args) {

        new FlatSearchApp();
    }
}