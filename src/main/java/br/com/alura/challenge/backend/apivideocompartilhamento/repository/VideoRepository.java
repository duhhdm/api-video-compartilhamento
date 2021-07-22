package br.com.alura.challenge.backend.apivideocompartilhamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.challenge.backend.apivideocompartilhamento.domain.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{

}
