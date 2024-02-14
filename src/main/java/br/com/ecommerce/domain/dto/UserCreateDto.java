package br.com.ecommerce.domain.dto;



import java.util.List;
import java.util.UUID;

public record UserCreateDto(UUID id,
                            String name,
                            String email,
                            String password,
                            byte[] profilePicture,
                            List<PhoneDto> phoneList,
                            List<AddressDto> addressList) {
}
