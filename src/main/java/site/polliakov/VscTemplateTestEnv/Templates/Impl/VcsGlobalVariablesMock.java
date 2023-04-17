package site.polliakov.VscTemplateTestEnv.Templates.Impl;

import org.apache.velocity.context.Context;
import org.springframework.stereotype.Component;
import site.polliakov.VscTemplateTestEnv.Templates.GlobalContextVariables;

// Ссылка на дкументацию: https://info.gosuslugi.ru/articles/Разработка_VM-шаблонов/
@Component
public class VcsGlobalVariablesMock implements GlobalContextVariables {
    // region variableNames
    private final String[] variableNames = new String[] {
            "orderid",
            "masterid",
            "oid",
            "masterOid",
            "firstName",
            "masterFirstName",
            "lastName",
            "masterLastName",
            "middleName",
            "masterMiddleName",
            "birthDate",
            "masterBirthDate",
            "gender",
            "masterGender",
            "citizenship",
            "masterCitizenship",
            "citizenshipCode",
            "masterCitizenshipCode",
            "birthdateCode",
            "masterBirthdateCode",
            "userOrgChief",
            "timezone",
            "masterUserOrgChief",
            "orgType",
            "masterOrgType",
            "leg",
            "inn",
            "legCode",
            "serviceId",
            "targetId",
            "homePhone",
            "mobilePhoneNumber",
            "contactPhoneNumber",
            "email"
    };
    // endregion

    public void addToContext(Context context) {
        for (var name : variableNames) {
            context.put(name, name.toUpperCase() + "_MOCK");
        }
    }
}
