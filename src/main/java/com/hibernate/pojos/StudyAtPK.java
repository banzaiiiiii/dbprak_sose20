package com.hibernate.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;


public class StudyAtPK implements Serializable
{
    private long studyAtPersonId;
    private long studyAtUniversityId;
    private int studyAtClassYear;

    @Column(name = "study_at_person_id")
    @Id
    public long getStudyAtPersonId()
    {
        return studyAtPersonId;
    }

    public void setStudyAtPersonId(final long studyAtPersonId)
    {
        this.studyAtPersonId = studyAtPersonId;
    }

    @Column(name = "study_at_university_id")
    @Id
    public long getStudyAtUniversityId()
    {
        return studyAtUniversityId;
    }

    public void setStudyAtUniversityId(final long studyAtUniversityId)
    {
        this.studyAtUniversityId = studyAtUniversityId;
    }

    @Column(name = "study_at_class_year")
    @Id
    public int getStudyAtClassYear()
    {
        return studyAtClassYear;
    }

    public void setStudyAtClassYear(final int studyAtClassYear)
    {
        this.studyAtClassYear = studyAtClassYear;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        final StudyAtPK studyAtPK = (StudyAtPK) o;

        if (studyAtPersonId != studyAtPK.studyAtPersonId)
        {
            return false;
        }
        if (studyAtUniversityId != studyAtPK.studyAtUniversityId)
        {
            return false;
        }
        if (studyAtClassYear != studyAtPK.studyAtClassYear)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (studyAtPersonId ^ (studyAtPersonId >>> 32));
        result = 31 * result + (int) (studyAtUniversityId ^ (studyAtUniversityId >>> 32));
        result = 31 * result + studyAtClassYear;
        return result;
    }
}
