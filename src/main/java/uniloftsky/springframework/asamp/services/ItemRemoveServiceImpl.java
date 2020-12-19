package uniloftsky.springframework.asamp.services;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import uniloftsky.springframework.asamp.model.Item;
import uniloftsky.springframework.asamp.model.ItemRemove;
import uniloftsky.springframework.asamp.services.repositories.ItemRemoveRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ItemRemoveServiceImpl implements ItemRemoveService {

    private final ItemRemoveRepository itemRemoveRepository;
    private final ItemService itemService;
    private final ItemTypeService itemTypeService;

    public ItemRemoveServiceImpl(ItemRemoveRepository itemRemoveRepository, ItemService itemService, ItemTypeService itemTypeService) {
        this.itemRemoveRepository = itemRemoveRepository;
        this.itemService = itemService;
        this.itemTypeService = itemTypeService;
    }

    @Override
    public Set<ItemRemove> findAll() {
        Set<ItemRemove> removes = new HashSet<>();
        itemRemoveRepository.findAll().iterator().forEachRemaining(removes::add);
        return removes;
    }

    @Override
    public ItemRemove findById(Long id) {
        Optional<ItemRemove> itemRemoveOptional = itemRemoveRepository.findById(id);
        if (itemRemoveOptional.isEmpty()) {
            throw new RuntimeException("Expected item remove not found!");
        }
        return itemRemoveOptional.get();
    }

    @Override
    public void delete(ItemRemove itemRemove) {
        itemRemoveRepository.delete(itemRemove);
    }

    @Override
    public ItemRemove save(ItemRemove itemRemove, Long itemTypeId) {
        Item foundItem = itemService.findByItemType_TypeName(itemTypeService.findById(itemTypeId).getTypeName());
        if ((foundItem.getCount() - itemRemove.getCount()) >= 0) {
            foundItem.setCount(foundItem.getCount() - itemRemove.getCount());
            itemService.save(foundItem);
            return itemRemoveRepository.save(itemRemove);
        }
        return null;
    }

    @Override
    public ItemRemove save(ItemRemove itemRemove) {
        return itemRemoveRepository.save(itemRemove);
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

        for(ItemRemove itemRemove : itemRemoveRepository.findAll()) {

            var row = sheet.createRow(i + 1);

            var cell = row.createCell(0);
            cell.setCellValue(itemRemove.getItemName().getItemName());

            cell = row.createCell(1);
            cell.setCellValue(itemRemove.getItemType().getTypeName());

            cell = row.createCell(2);
            cell.setCellValue(itemRemove.getCount());

            cell = row.createCell(3);
            cell.setCellValue(String.valueOf(itemRemove.getPrice()));

            cell = row.createCell(4);
            cell.setCellValue(itemRemove.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(itemRemove.getCounterAgent().getFirstName() + " " + itemRemove.getCounterAgent().getLastName() + " " + itemRemove.getCounterAgent().getMiddleName());

            i++;
        }

    }
}
