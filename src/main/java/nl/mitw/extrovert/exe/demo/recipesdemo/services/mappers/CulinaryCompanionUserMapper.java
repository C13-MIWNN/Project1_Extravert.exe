package nl.mitw.extrovert.exe.demo.recipesdemo.services.mappers;

import nl.mitw.extrovert.exe.demo.recipesdemo.dtos.CulinaryCompanionUserFormDTO;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.CulinaryCompanionUser;

/**
 * Nadine Beck
 * Omschrijving
 */
public class CulinaryCompanionUserMapper {
    public static CulinaryCompanionUser fromDTO(CulinaryCompanionUserFormDTO dto) {
        CulinaryCompanionUser user = new CulinaryCompanionUser();
        user.setUsername(dto.getName());
        user.setPassword(dto.getPassword());

        return user;
    }
}
