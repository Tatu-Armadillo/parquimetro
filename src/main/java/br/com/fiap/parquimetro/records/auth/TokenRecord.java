package br.com.fiap.parquimetro.records.auth;

import java.util.Set;

public record TokenRecord(
        String userName,
        String token,
        Set<String> permissions) {

}
