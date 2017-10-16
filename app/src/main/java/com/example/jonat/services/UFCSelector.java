package com.example.jonat.services;

import com.example.jonat.services.Models.Title;
import com.karumi.dividers.Direction;
import com.karumi.dividers.Position;
import com.karumi.dividers.selector.Selector;

import java.util.EnumSet;
import java.util.List;

/**
 * Created by jonat on 10/16/2017.
 */
public class UFCSelector implements Selector {

    private final List<Title> titles;
    private final int highRatingThreshold;

    public UFCSelector(List<Title> titles, int highRatingThreshold) {
        this.titles = titles;
        this.highRatingThreshold = highRatingThreshold;
    }

    @Override
    public boolean isPositionSelected(Position position) {
        return titles.get(position.getAbsoluteIndex()).getId() >= highRatingThreshold;
    }

    @Override
    public EnumSet<Direction> getDirectionsByPosition(Position position) {
        return EnumSet.allOf(Direction.class);
    }
}
