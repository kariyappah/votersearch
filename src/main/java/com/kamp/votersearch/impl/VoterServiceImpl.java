package com.kamp.votersearch.impl;

import com.kamp.votersearch.Voter;
//import com.kamp.votersearch.VoterRepository;
import com.kamp.votersearch.VoterService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class VoterServiceImpl implements VoterService {
//    private final VoterRepository voterRepository;
    private final ResourceLoader resourceLoader;
    private boolean isUploaded = false;
    List<Voter> voters = new ArrayList<>();

    public VoterServiceImpl(/*VoterRepository voterRepository,*/ ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
//        this.voterRepository = voterRepository;
    }

    @Override
    public List<Voter> findAllVoters() {
        if (!isUploaded) {
            addVoter(null);
        }
        return voters;
//        return voterRepository.findAll();
    }

    @Override
    public List<Voter> findAllVoters(String keyword) {
        if (keyword != null) {
//            return voterRepository.search(keyword);
            return voters.stream().filter(s->s.getFullName().startsWith(keyword)).toList();
        }
//        return voterRepository.findAll();
        return voters;
    }

    @Override
    public Voter findByFullName(String firstName) {
//        return voterRepository.findByFullName(firstName);
        return voters.stream().filter(s->s.getFullName().equals(firstName)).findFirst().orElse(null);
    }

    @Override
    public void addVoter(Voter voter) {
        if (!isUploaded) {
            exelUpload();
            isUploaded = true;
        } else if (voter == null) {
            voter = new Voter();
            voter.setPartNo(13d);
            voter.setFullName("Kariyappa Hosarannavar");
            voter.setRelationName("Shivappa");
            exelUpload();
            isUploaded = true;
            voters.add(voter);
//            voterRepository.save(voter);
        }
    }

    @Override
    public void exelUpload() {
        Resource resource = resourceLoader.getResource("classpath:validvoters.xlsx");
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(resource.getInputStream());
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Voter voter = new Voter();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> voter.setPartNo((double)cell.getNumericCellValue());
                        case 1 -> voter.setFullName(cell.getStringCellValue());
                        case 2 -> voter.setRelationName(cell.getStringCellValue());
                        case 3 -> voter.setAddress(cell.getStringCellValue());
                        case 4 -> voter.setQualification(cell.getStringCellValue());
                        case 5 -> voter.setBusiness(cell.getStringCellValue());
                        case 6 -> voter.setAge((double) cell.getNumericCellValue());
                        case 7 -> voter.setSex(cell.getStringCellValue());
                        case 8 -> voter.setPhoto(cell.getStringCellValue());
                        default -> {

                        }
                    }
                    cellIndex++;
                }
                voters.add(voter);
            }
//            voterRepository.saveAll(voters);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
