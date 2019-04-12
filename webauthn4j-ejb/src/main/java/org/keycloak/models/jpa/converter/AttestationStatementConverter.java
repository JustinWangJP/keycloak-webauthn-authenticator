/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.keycloak.models.jpa.converter;

import com.webauthn4j.data.attestation.statement.AttestationStatement;

import javax.persistence.AttributeConverter;

public class AttestationStatementConverter implements AttributeConverter<AttestationStatement, String> {

    private JsonConverter converter = new JsonConverter();

    @Override
    public String convertToDatabaseColumn(AttestationStatement attribute) {
        return converter.writeValueAsString(attribute);
    }

    @Override
    public AttestationStatement convertToEntityAttribute(String dbData) {
        return converter.readValue(dbData, AttestationStatement.class);
    }
}
