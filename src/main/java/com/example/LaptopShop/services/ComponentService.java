package com.example.LaptopShop.services;

import com.example.LaptopShop.models.Brand;
import com.example.LaptopShop.models.components.*;
import com.example.LaptopShop.repositories.BrandRepository;
import com.example.LaptopShop.repositories.components.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ComponentService {
    private final BrandRepository brandRepository;
    private final CPURepository cpuRepository;
    private final RAMRepository ramRepository;
    private final ScreenRepository screenRepository;
    private final DiskRepository diskRepository;
    private final BatteryRepository batteryRepository;
    private final OSRepository osRepository;

    public ComponentService(BrandRepository brandRepository, CPURepository cpuRepository, RAMRepository ramRepository, ScreenRepository screenRepository, DiskRepository diskRepository, BatteryRepository batteryRepository, OSRepository osRepository) {
        this.brandRepository = brandRepository;
        this.cpuRepository = cpuRepository;
        this.ramRepository = ramRepository;
        this.screenRepository = screenRepository;
        this.diskRepository = diskRepository;
        this.batteryRepository = batteryRepository;
        this.osRepository = osRepository;
    }


    public List<Brand> getAllBrand(){
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return brandRepository.findAll(sort);
    }
    public List<CPU> getAllCPU(){
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return cpuRepository.findAll(sort);
    }
    public List<RAM> getAllRAM(){
        Sort sort = Sort.by(Sort.Direction.ASC, "size");
        return ramRepository.findAll(sort);
    }

    public List<Screen> getAllScreen(){
        Sort sort = Sort.by(

                Sort.Order.asc("panel"),
                Sort.Order.asc("size"),
                Sort.Order.asc("touch")
        );
        return screenRepository.findAll(sort);
    }

    public List<Disk> getAllDisk(){
        Sort sort = Sort.by(
                Sort.Order.asc("type"),
                Sort.Order.asc("size")
        );
        return diskRepository.findAll(sort);
    }

    public List<Battery> getAllBattery(){
        Sort sort = Sort.by(Sort.Direction.ASC, "capacity");
        return batteryRepository.findAll(sort);
    }

    public List<OS> getAllOS(){
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return osRepository.findAll(sort);
    }
}