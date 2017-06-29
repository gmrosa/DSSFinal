package br.com.furb.model.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.furb.cripto.Sha256;
import br.com.furb.model.User;
import br.com.furb.model.dto.UserDTO;

public class UserDTOConverter {

    public static List<UserDTO> toDTO(List<User> users) {
        if (users == null) {
            return new ArrayList<>();
        }
        return users.stream().map(user -> UserDTOConverter.toDTO(user)).collect(Collectors.toList());
    }

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();

        dto.id = user.getId();
        dto.name = user.getName();
        dto.role = user.getRole();
        dto.card = user.getCard();
        dto.isValid = user.getEssence() == null ? false : user.getEssence().equals(Sha256.getHash(user.toString()));

        return dto;
    }

}
