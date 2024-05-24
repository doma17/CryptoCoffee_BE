package inu.spp.cryptocoffee.domain.member.repository;

import inu.spp.cryptocoffee.domain.company.entity.CompanyEntity;
import inu.spp.cryptocoffee.domain.member.dto.MemberStatus;
import inu.spp.cryptocoffee.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepositroy extends JpaRepository<MemberEntity, Long> {

    List<MemberEntity> findByCompanyAndStatus(CompanyEntity company, MemberStatus memberStatus);

}