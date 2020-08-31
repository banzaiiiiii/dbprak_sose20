package Models;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "company", schema = "public", catalog = "socialnetwork")
public class Company {

    private long companyId;

    @Id
    @Column(name = "company_id")
    public long getCompanyId() {
            return companyId;
        }
        public void setCompanyId(long companyId) {
            this.companyId = companyId;
        }

    private long companyOrganisationId;
    @Column(name = "company_organisation_id")
    public long getCompanyOrganisationId() {
        return companyOrganisationId;
    }

    public void setCompanyOrganisationId(long companyOrganisationId) {
        this.companyOrganisationId = companyOrganisationId;
    }

    private long companyCountryId;
    @Column(name = "company_country_id")
    public long getCompanyCountryId() {
        return companyCountryId;
    }

    public void setCompanyCountryId(long companyCountryId) {
        this.companyCountryId = companyCountryId;
    }



}
