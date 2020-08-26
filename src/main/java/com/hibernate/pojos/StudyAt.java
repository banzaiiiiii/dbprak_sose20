package com.hibernate.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name = "study_at", schema = "public", catalog = "socialnetwork")
@IdClass(StudyAtPK.class)
public class StudyAt
{
    private long studyAtPersonId;
    private long studyAtUniversityId;
    private int studyAtClassYear;

    @Id
    @Column(name = "study_at_person_id")
    public long getStudyAtPersonId()
    {
        return studyAtPersonId;
    }

    public void setStudyAtPersonId(final long studyAtPersonId)
    {
        this.studyAtPersonId = studyAtPersonId;
    }

    @Id
    @Column(name = "study_at_university_id")
    public long getStudyAtUniversityId()
    {
        return studyAtUniversityId;
    }

    public void setStudyAtUniversityId(final long studyAtUniversityId)
    {
        this.studyAtUniversityId = studyAtUniversityId;
    }

    @Id
    @Column(name = "study_at_class_year")
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

        final StudyAt studyAt = (StudyAt) o;

        if (studyAtPersonId != studyAt.studyAtPersonId)
        {
            return false;
        }
        if (studyAtUniversityId != studyAt.studyAtUniversityId)
        {
            return false;
        }
        if (studyAtClassYear != studyAt.studyAtClassYear)
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
