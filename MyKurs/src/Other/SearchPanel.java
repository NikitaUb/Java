package Other;

import App.FlatSearchApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class SearchPanel extends JPanel {
    private JTextField districtField;
    private JTextField minTotalAreaField;
    private JTextField maxTotalAreaField;
    private JTextField minPriceField;
    private JTextField maxPriceField;
    private JTextArea resultArea;

    public SearchPanel() {

        // Создаем компоненты для ввода параметров поиска
        districtField = new JTextField(10);
        minTotalAreaField = new JTextField(5);
        maxTotalAreaField = new JTextField(5);
        minPriceField = new JTextField(10);
        maxPriceField = new JTextField(10);

        // Создаем кнопку для запуска поиска
        JButton searchButton = new JButton("Поиск");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Извлекаем значения параметров поиска
                String district = districtField.getText();
                double minTotalArea = Double.parseDouble(minTotalAreaField.getText());
                double maxTotalArea = Double.parseDouble(maxTotalAreaField.getText());
                double minPrice = Double.parseDouble(minPriceField.getText());
                double maxPrice = Double.parseDouble(maxPriceField.getText());

                // Ищем квартиры, соответствующие параметрам поиска
                List<Flat> results = new ArrayList<>();
                List<Flat> flatsList = FlatSearchApp.getFlatsList();
                for (Flat flat : flatsList) {
                        if (district.equals("") || flat.getDistrict().equals(district)) {
                        double totalArea = flat.getTotalArea();
                        double price = flat.getTotalPrice();
                        if (totalArea >= minTotalArea && totalArea <= maxTotalArea && price >= minPrice && price <= maxPrice) {
                            results.add(flat);
                        }
                    }
                }

                // Отображаем результаты поиска
                resultArea.setText("");
                for (Flat flat : results) {
                    resultArea.append(String.format("%s, %s, %d/%d, %.2f/%.2f м², %d комнат, %s, %s Тугриков%n",
                            flat.getDistrict(),
                            flat.getBuildingCategory(), flat.getFloor(), flat.getBuildingFloors(), flat.getTotalArea(), flat.getLivingArea(),
                            flat.getRooms(), flat.getAddress(),  flat.getTotalPrice()));
                }
            }
        });

        // Создаем компонент для отображения результатов поиска
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Создаем панели для компонентов ввода параметров поиска
        JPanel districtPanel = new JPanel();
        districtPanel.add(new JLabel("Район:"));
        districtPanel.add(districtField);

        JPanel totalAreaPanel = new JPanel();
        totalAreaPanel.add(new JLabel("Общая площадь:"));
        totalAreaPanel.add(new JLabel("От"));
        totalAreaPanel.add(minTotalAreaField);
        totalAreaPanel.add(new JLabel("До"));
        totalAreaPanel.add(maxTotalAreaField);

        JPanel pricePanel = new JPanel();
        pricePanel.add(new JLabel("Стоимость:"));
        pricePanel.add(new JLabel("От (Тугриков)"));
        pricePanel.add(minPriceField);
        pricePanel.add(new JLabel("До (Тугриков)"));
        pricePanel.add(maxPriceField);


        // Создаем панель для компонентов поиска
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.add(districtPanel);
        searchPanel.add(totalAreaPanel);
        searchPanel.add(pricePanel);
        searchPanel.add(searchButton);

        // Создаем панель для отображения результатов поиска
        JPanel resultPanel = new JPanel();
        resultPanel.add(scrollPane);

        // Добавляем компоненты на панель поиска
        add(searchPanel);
        add(resultPanel);
    }
}