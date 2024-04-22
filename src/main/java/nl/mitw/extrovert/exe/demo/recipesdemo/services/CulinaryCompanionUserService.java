package nl.mitw.extrovert.exe.demo.recipesdemo.services;

import nl.mitw.extrovert.exe.demo.recipesdemo.dtos.CulinaryCompanionUserFormDTO;
import nl.mitw.extrovert.exe.demo.recipesdemo.model.CulinaryCompanionUser;
import nl.mitw.extrovert.exe.demo.recipesdemo.repositories.CulinaryCompanionUserRepository;
import nl.mitw.extrovert.exe.demo.recipesdemo.services.mappers.CulinaryCompanionUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CulinaryCompanionUserService implements UserDetailsService {
    private final CulinaryCompanionUserRepository culinaryCompanionUserRepository;
    private final PasswordEncoder passwordEncoder;

    public CulinaryCompanionUserService(CulinaryCompanionUserRepository culinaryCompanionUserRepository, PasswordEncoder passwordEncoder) {
        this.culinaryCompanionUserRepository = culinaryCompanionUserRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return culinaryCompanionUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public boolean userExists(String username) {
        return culinaryCompanionUserRepository.findByUsername(username).isPresent();
    }

    public void saveUser(CulinaryCompanionUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        culinaryCompanionUserRepository.save(user);
    }

    public void saveUser (CulinaryCompanionUserFormDTO dto) {
        saveUser(CulinaryCompanionUserMapper.fromDTO(dto));
    }

    public boolean isNotInitialised() {
        return culinaryCompanionUserRepository.count() == 0;
    }
}
