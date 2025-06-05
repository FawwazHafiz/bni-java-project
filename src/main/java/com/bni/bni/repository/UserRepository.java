package com.bni.bni.repository;

import com.bni.bni.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Cek user berdasarkan username (untuk login)
    Optional<User> findByUsername(String username);

    // Cek apakah username sudah digunakan
    boolean existsByUsername(String username);

    // Tambahan opsional: cek apakah email sudah digunakan (jika validasi email diperlukan)
    boolean existsByEmailAddress(String emailAddress);

    // Cari user berdasarkan email
    Optional<User> findByEmailAddress(String emailAddress);
}
