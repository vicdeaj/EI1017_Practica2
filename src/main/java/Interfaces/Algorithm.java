package Interfaces;

import Table.Table;
public interface Algorithm <T extends Table, S, R>{ //T = table used for training, S = Sample for estimation, R = return value of estimate

    void train(T data);
    R estimate(S sample);
}
