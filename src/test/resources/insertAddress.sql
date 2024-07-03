INSERT INTO
    tbl_address (
        person_id,
        identifier_id,
        zip_code,
        street,
        number,
        complement,
        neighborhood,
        city,
        uf,
        country,
        main_address
    )
VALUES (
        1,
        13,
        '12345678',
        'Rua Exemplo',
        '123',
        'Bairro Exemplo',
        'Casa 6',
        'Cidade Exemplo',
        'CA',
        'London',
        true
    );

INSERT INTO
    tbl_address (
        person_id,
        identifier_id,
        zip_code,
        street,
        number,
        complement,
        neighborhood,
        city,
        uf,
        country,
        main_address
    )
VALUES (
        2,
        14,
        '05432450',
        'Rua Exemplo',
        '432',
        'Bairro Exemplo',
        'Apt 5D',
        'Cidade Exemplo',
        'NY',
        'United States',
        false
    );

INSERT INTO
    tbl_address (
        person_id,
        identifier_id,
        zip_code,
        street,
        number,
        complement,
        neighborhood,
        city,
        uf,
        country,
        main_address
    )
VALUES (
        3,
        15,
        '34567867',
        'Rua Exemplo',
        '432',
        'Bairro Exemplo',
        '',
        'Cidade Exemplo',
        'BC',
        'Canada',
        false
    );