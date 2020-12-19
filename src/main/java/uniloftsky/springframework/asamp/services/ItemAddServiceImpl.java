package uniloftsky.springframework.asamp.services;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import uniloftsky.springframework.asamp.model.Item;
import uniloftsky.springframework.asamp.model.ItemAdd;
import uniloftsky.springframework.asamp.services.repositories.ItemAddRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ItemAddServiceImpl implements ItemAddService {

    private final ItemAddRepository itemAddRepository;
    private final ItemTypeService itemTypeService;
    private final ItemService itemService;

    public ItemAddServiceImpl(ItemAddRepository itemAddRepository, ItemTypeService itemTypeService, ItemService itemService) {
        this.itemAddRepository = itemAddRepository;
        this.itemTypeService = itemTypeService;
        this.itemService = itemService;
    }

    @Override
    public Set<ItemAdd> findAll() {
        Set<ItemAdd> adds = new HashSet<>();
        itemAddRepository.findAll().iterator().forEachRemaining(adds::add);
        return adds;
    }

    @Override
    public ItemAdd findById(Long id) {
        Optional<ItemAdd> itemAddOptional = itemAddRepository.findById(id);
        if (itemAddOptional.isEmpty()) {
            throw new RuntimeException("Expected item add not found!");
        }
        return itemAddOptional.get();
    }

    @Override
    public void delete(ItemAdd itemAdd) {
        itemAddRepository.delete(itemAdd);
    }

    @Override
    public ItemAdd save(ItemAdd itemAdd, Long itemTypeId) {
        Item foundItem = itemService.findByItemType_TypeName(itemTypeService.findById(itemTypeId).getTypeName());
        foundItem.setCount(foundItem.getCount() + itemAdd.getCount());
        itemService.save(foundItem);
        return itemAddRepository.save(itemAdd);
    }

    @Override
    public ItemAdd save(ItemAdd itemAdd) {
        return itemAddRepository.save(itemAdd);
    }

    @Override
    public void write(String fileName) throws IOException {
        var workbook = new XSSFWorkbook();
        var sheet = createSheet(workbook);
        createHeader(workbook, sheet);
        createCells(workbook, sheet);
        try(var outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
        }
        workbook.close();
    }

    @Override
    public Sheet createSheet(XSSFWorkbook workbook) {
        var sheet = workbook.createSheet("Клиенты");
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 6000);
        sheet.setColumnWidth(2, 4000);
        return sheet;
    }

    @Override
    public void createHeader(XSSFWorkbook workbook, Sheet sheet) {
        var header = sheet.createRow(0);

        var headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        var font = workbook.createFont();
        font.setFontName("Times New Roman");
        headerStyle.setFont(font);

        var headerCell = header.createCell(0);
        headerCell.setCellValue("Назва");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Тип");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Кількість");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("Ціна");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(4);
        headerCell.setCellValue("Дата");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(5);
        headerCell.setCellValue("Контрагент");
        headerCell.setCellStyle(headerStyle);
    }

    @Override
    public void createCells(XSSFWorkbook workbook, Sheet sheet) {
        var style = workbook.createCellStyle();
        style.setWrapText(true);
        sheet.setDefaultColumnStyle(1, style);

        int i = 0;

        for(ItemAdd itemAdd : itemAddRepository.findAll()) {

            var row = sheet.createRow(i + 1);

            var cell = row.createCell(0);
            cell.setCellValue(itemAdd.getItemName().getItemName());

            cell = row.createCell(1);
            cell.setCellValue(itemAdd.getItemType().getTypeName());

            cell = row.createCell(2);
            cell.setCellValue(itemAdd.getCount());

            cell = row.createCell(3);
            cell.setCellValue(String.valueOf(itemAdd.getPrice()));

            cell = row.createCell(4);
            cell.setCellValue(itemAdd.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(itemAdd.getCounterAgent().getFirstName() + " " + itemAdd.getCounterAgent().getLastName() + " " + itemAdd.getCounterAgent().getMiddleName());

            i++;
        }

    }
}
