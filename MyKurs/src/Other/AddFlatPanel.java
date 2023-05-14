package Other;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static App.FlatSearchApp.getFlatsList;

public class AddFlatPanel extends JPanel {
    private JTextField districtField;
    private JTextField buildingCategoryField;
    private JTextField buildingFloorsField;
    private JTextField floorField;
    private JTextField totalAreaField;
    private JTextField livingAreaField;
    private JTextField roomsField;
    private JTextField addressField;
    private JTextField priceField;

    public AddFlatPanel() {

        // Создаем компоненты для ввода параметров квартиры
        districtField = new JTextField(10);
        buildingCategoryField = new JTextField(10);
        buildingFloorsField = new JTextField(5);
        floorField = new JTextField(5);
        totalAreaField = new JTextField(10);
        livingAreaField = new JTextField(10);
        roomsField = new JTextField(5);
        addressField = new JTextField(20);
        priceField = new JTextField(20);

        // Создаем кнопку для добавления квартиры
        JButton addButton = new JButton("Добавить квартиру");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Извлекаем значения параметров квартиры
                String district = districtField.getText();
                String buildingCategory = buildingCategoryField.getText();
                int buildingFloors = 0;
                int floor = 0;
                double totalArea = 0;
                double livingArea = 0;
                int rooms = 0;
                String address = addressField.getText();
                double price = 0;
                if (!district.matches("[а-яА-Я]+")) {
                    JOptionPane.showMessageDialog(null, "Ошибка ввода данных: Поле 'Район' должно состоять только из букв.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!buildingCategory.matches("[а-яА-Я]+")) {
                    JOptionPane.showMessageDialog(null, "Ошибка ввода данных: Поле 'Категория дома' должно состоять только из букв.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    buildingFloors = Integer.parseInt(buildingFloorsField.getText());
                    floor = Integer.parseInt(floorField.getText());
                    totalArea = Double.parseDouble(totalAreaField.getText());
                    livingArea = Double.parseDouble(livingAreaField.getText());
                    rooms = Integer.parseInt(roomsField.getText());
                    price = Double.parseDouble(priceField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка ввода данных: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                List<Flat> NewflatsList = new ArrayList<>();
                NewflatsList.add(new Flat(district, buildingCategory, buildingFloors, floor, totalArea, livingArea, rooms, address, price));
                getFlatsList().addAll(NewflatsList);
                // Сохраняем список квартир в файл
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("flats.txt", true))) {
                    writer.write(String.format("%s|%s|%d|%d|%.2f|%.2f|%d|%s|%s%n", district, buildingCategory, buildingFloors, floor, totalArea, livingArea, rooms, address, price));
                    writer.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // Очищаем поля ввода параметров квартиры
                districtField.setText("");
                buildingCategoryField.setText("");
                buildingFloorsField.setText("");
                floorField.setText("");
                totalAreaField.setText("");
                livingAreaField.setText("");
                roomsField.setText("");
                addressField.setText("");
                priceField.setText("");
            }
        });

        // Создаем панели для компонентов ввода параметров квартиры
        JPanel districtPanel = new JPanel();
        districtPanel.add(new JLabel("Район:"));
        districtPanel.add(districtField);

        JPanel buildingCategoryPanel = new JPanel();
        buildingCategoryPanel.add(new JLabel("Категория дома:"));
        buildingCategoryPanel.add(buildingCategoryField);

        JPanel buildingFloorsPanel = new JPanel();
        buildingFloorsPanel.add(new JLabel("Этажность дома:"));
        buildingFloorsPanel.add(buildingFloorsField);

        JPanel floorPanel = new JPanel();
        floorPanel.add(new JLabel("Этаж:"));
        floorPanel.add(floorField);

        JPanel totalAreaPanel = new JPanel();
        totalAreaPanel.add(new JLabel("Общая площадь:"));
        totalAreaPanel.add(totalAreaField);

        JPanel livingAreaPanel = new JPanel();
        livingAreaPanel.add(new JLabel("Жилая площадь:"));
        livingAreaPanel.add(livingAreaField);

        JPanel roomsPanel = new JPanel();
        roomsPanel.add(new JLabel("Комнаты:"));
        roomsPanel.add(roomsField);

        JPanel addressPanel = new JPanel();
        addressPanel.add(new JLabel("Адрес:"));
        addressPanel.add(addressField);

        JPanel pricePanel = new JPanel();
        pricePanel.add(new JLabel("Cтоимость:"));
        pricePanel.add(priceField);

        // Создаем панель для компонентов ввода параметров квартиры
        JPanel addFlatPanel = new JPanel();
        addFlatPanel.setLayout(new BoxLayout(addFlatPanel, BoxLayout.Y_AXIS));
        addFlatPanel.add(districtPanel);
        addFlatPanel.add(buildingCategoryPanel);
        addFlatPanel.add(buildingFloorsPanel);
        addFlatPanel.add(floorPanel);
        addFlatPanel.add(totalAreaPanel);
        addFlatPanel.add(livingAreaPanel);
        addFlatPanel.add(roomsPanel);
        addFlatPanel.add(addressPanel);
        addFlatPanel.add(pricePanel);
        addFlatPanel.add(addButton);

        // Добавляем панель для компонентов ввода параметров квартиры на панель добавления квартиры
        add(addFlatPanel);
    }
}