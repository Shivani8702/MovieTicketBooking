package com.cg.mtb.util;

import com.cg.mtb.dto.TierDto;
import com.cg.mtb.entity.TheatreEntity;
import com.cg.mtb.entity.TierEntity;

public class TierUtil {

    // Convert Entity -> DTO
    public static TierDto toDto(TierEntity tier) {
        if (tier == null) {
            return null;
        }
        return new TierDto(
            tier.getTierId(),
            (tier.getTheatre() != null) ? tier.getTheatre().getTheatreId() : 0, // Handle potential null
            tier.getTierName(),
            tier.getTicketPriceMultiplier()
        );
    }

    // Convert DTO -> Entity (For Create)
    public static TierEntity toEntity(TierDto dto) {
        if (dto == null) {
            return null;
        }
        TierEntity tier = new TierEntity();
        tier.setTierName(dto.getTierName());
        tier.setTicketPriceMultiplier(dto.getTicketPriceMultiplier());
        return tier;
    }

    // Convert DTO -> Entity (For Update) with Theatre Relationship
    public static TierEntity updateEntity(TierEntity tier, TierDto dto, TheatreEntity theatre) {
        if (tier == null || dto == null || theatre == null) {
            throw new IllegalArgumentException("Tier, DTO, or Theatre cannot be null");
        }
        tier.setTierName(dto.getTierName());
        tier.setTicketPriceMultiplier(dto.getTicketPriceMultiplier());
        tier.setTheatre(theatre);
        return tier;
    }
    
    public static TierEntity toEntity(TierDto dto, TheatreEntity theatre) {
        if (dto == null || theatre == null) {
            throw new IllegalArgumentException("DTO or Theatre cannot be null");
        }
        TierEntity tier = new TierEntity();
        tier.setTierName(dto.getTierName());
        tier.setTicketPriceMultiplier(dto.getTicketPriceMultiplier());
        tier.setTheatre(theatre);  // Setting the theatre reference
        return tier;
    }
}
