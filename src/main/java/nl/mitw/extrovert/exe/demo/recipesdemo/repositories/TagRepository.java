package nl.mitw.extrovert.exe.demo.recipesdemo.repositories;

import nl.mitw.extrovert.exe.demo.recipesdemo.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TagRepository extends JpaRepository <Tag, Long> {
}
