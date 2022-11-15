package com.fiveplus.platform.helpModels;

import com.fiveplus.platform.model.Child;
import com.fiveplus.platform.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StartProfile {
    User parent;
    Child firstChild;
}
