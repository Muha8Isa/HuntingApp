package jaktia.huntingapp.service.serviceImpl;

import jaktia.huntingapp.dto.AppUserDto;
import jaktia.huntingapp.entity.AppUser;
import jaktia.huntingapp.exceptions.DataDuplicateException;
import jaktia.huntingapp.exceptions.DataNotFoundException;
import jaktia.huntingapp.repository.AppUserRepository;
import jaktia.huntingapp.service.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;


@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    @Transactional(rollbackOn = {Exception.class}) //If an exception was thrown, rollback all changes in Database.
    public AppUserDto register(AppUserDto dto) {
        // Step 1: Check the methods' params
        if(dto == null) throw new IllegalArgumentException("UserDto data was null");
        if(dto.getUsername() == null || dto.getPassword() == null) throw new IllegalArgumentException("username or password data was null");

        // Step 2: Check the username that should not be duplicated.
        boolean  isExist = appUserRepository.existsByUsername(dto.getUsername());
        if(isExist) throw new DataDuplicateException("duplicate username error");

        // Step 3: convert the dto to entity
        AppUser convertedToEntity = modelMapper.map(dto, AppUser.class);

        // Step 4: Execute the save method of UserRepository
        AppUser createdEntity = appUserRepository.save(convertedToEntity);

        // Step 5: Convert the created entity to dto.
        AppUserDto convertedToDto = modelMapper.map(createdEntity, AppUserDto.class);

        return convertedToDto;
    }

    @Override //ReadOnly method, so no need to use transactional.
    public Map<String, Object> findByUsername(String username) {
        if(username == null) throw new IllegalArgumentException("username was null");
        AppUser user = appUserRepository.selectByUsername(username).orElseThrow(()->new DataNotFoundException("username was not found error"));
        Map<String, Object> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("active", user.isActive());
        return map;
    }

    @Override
    @Transactional
    public void disableUserByUsername(String username) {
        if(username == null) throw new IllegalArgumentException("username was null");
        if(!appUserRepository.existsByUsername(username)) throw new DataNotFoundException("username was not valid");
        appUserRepository.disableUserByUsername(username, false);
    }
}
