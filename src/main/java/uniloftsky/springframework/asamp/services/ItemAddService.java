package uniloftsky.springframework.asamp.services;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import uniloftsky.springframework.asamp.model.ItemAdd;

import java.io.IOException;
import java.util.Set;

public interface ItemAddService {

    Set<ItemAdd> findAll();
    ItemAdd findById(Long id);
    void delete(ItemAdd itemAdd);
    ItemAdd save(ItemAdd itemAdd, Long itemTypeId);
    ItemAdd save(ItemAdd itemAdd);
    void write(String fileName) throws IOException;
    Sheet createSheet(XSSFWorkbook workbook);
    void createHeader(XSSFWorkbook workbook, Sheet sheet);
    void createCells(XSSFWorkbook workbook, Sheet sheet);

}
