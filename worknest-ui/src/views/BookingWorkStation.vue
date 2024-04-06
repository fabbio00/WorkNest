<template>

    <v-container>

        <v-row justify="center">
            <v-col cols="12" md="8" lg="6">
                <v-card class="mb-5" elevation="2">
                <v-card-text>
                    <v-date-picker v-model="booking.startDate" no-title scrollable></v-date-picker>
                </v-card-text>
                <v-card-actions>
                    <v-btn color="primary" class="mr-4" @click="findOccupiedDesks()">Find Available Desks</v-btn>
                </v-card-actions>
                </v-card>
            </v-col>
        </v-row>

        <v-row justify="center" class="mb-5">
            <v-col cols="12" md="10">

                <v-row justify="center">
                    <v-col cols="12">
                        <div class="svg-container" style="text-align: center;">
                            <svg v-show="isSvgVisible" width="900" height="600" xmlns="http://www.w3.org/2000/svg">
                                <defs>
                                <filter id="svg_21_blur">
                                <feGaussianBlur stdDeviation="0" in="SourceGraphic"/>
                                </filter>
                                </defs>
                                <g>
                                <title>Layer 1</title>
                                <line stroke="#000" stroke-linecap="undefined" stroke-linejoin="undefined" id="svg_5" y2="585.08183" x2="17" y1="53" x1="17" fill="none"/>
                                <line stroke="#000" stroke-linecap="undefined" stroke-linejoin="undefined" id="svg_7" y2="585" x2="507.18118" y1="585" x1="17" fill="none"/>
                                <g id="svg_29">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_21" height="56" width="28" y="81" x="80" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_22" height="25" width="13" y="96" x="67" fill="#f2efef" />
                                </g>
                                <g id="svg_30">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_24" cy="109" cx="91" fill="#ffffff" />
                                <ellipse data-id="3a6d0494-9f28-4e1d-a79a-d39dbf0b176a" stroke="#000" ry="12" rx="12" id="svg_28" cy="109" cx="91" fill="green" @click="bookingDesk($event)" />
                                </g>
                                <g id="svg_33">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_31" height="56" width="28" y="148" x="81" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_32" height="25" width="13" y="163" x="68" fill="#f2efef" />
                                </g>
                                <g id="svg_36">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_34" cy="176" cx="92" fill="#ffffff"/>
                                <ellipse data-id="3da9bb25-4c6d-46fa-aa71-8c18a69e305b" stroke="#000" ry="12" rx="12" id="svg_35" cy="176" cx="92" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 141.5 109)" id="svg_51">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_49" height="56" width="28" y="81" x="134" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_50" height="25" width="13" y="96" x="121" fill="#f2efef"/>
                                </g>
                                <g id="svg_54">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_52" cy="109" cx="138" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_53" cy="109" cx="138" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 142.5 176)" id="svg_57">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_55" height="56" width="28" y="148" x="135" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_56" height="25" width="13" y="163" x="122" fill="#f2efef"/>
                                </g>
                                <g id="svg_60">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_58" cy="175" cx="139" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_59" cy="175" cx="139" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_63">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_61" height="56" width="28" y="78" x="242" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_62" height="25" width="13" y="93" x="229" fill="#f2efef"/>
                                </g>
                                <g id="svg_66">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_64" cy="106" cx="253" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_65" cy="106" cx="253" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_69">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_67" height="56" width="28" y="145" x="243" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_68" height="25" width="13" y="160" x="230" fill="#f2efef"/>
                                </g>
                                <g id="svg_72">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_70" cy="173" cx="254" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_71" cy="173" cx="254" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 303.5 106)" id="svg_75">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_73" height="56" width="28" y="78" x="296" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_74" height="25" width="13" y="93" x="283" fill="#f2efef"/>
                                </g>
                                <g id="svg_78">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_76" cy="106" cx="300" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_77" cy="106" cx="300" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 304.5 173)" id="svg_81">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_79" height="56" width="28" y="145" x="297" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_80" height="25" width="13" y="160" x="284" fill="#f2efef"/>
                                </g>
                                <g id="svg_84">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_82" cy="172" cx="301" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_83" cy="172" cx="301" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_87">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_85" height="56" width="28" y="77" x="408" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_86" height="25" width="13" y="92" x="395" fill="#f2efef"/>
                                </g>
                                <g id="svg_90">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_88" cy="105" cx="419" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_89" cy="105" cx="419" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_93">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_91" height="56" width="28" y="144" x="409" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_92" height="25" width="13" y="159" x="396" fill="#f2efef"/>
                                </g>
                                <g id="svg_96">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_94" cy="172" cx="420" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_95" cy="172" cx="420" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 469.5 105)" id="svg_99">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_97" height="56" width="28" y="77" x="462" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_98" height="25" width="13" y="92" x="449" fill="#f2efef"/>
                                </g>
                                <g id="svg_102">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_100" cy="105" cx="466" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_101" cy="105" cx="466" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 470.5 172)" id="svg_105">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_103" height="56" width="28" y="144" x="463" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_104" height="25" width="13" y="159" x="450" fill="#f2efef"/>
                                </g>
                                <g id="svg_108">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_106" cy="171" cx="467" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_107" cy="171" cx="467" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_111">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_109" height="56" width="28" y="275" x="84" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_110" height="25" width="13" y="290" x="71" fill="#f2efef"/>
                                </g>
                                <g id="svg_114">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_112" cy="303" cx="95" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_113" cy="303" cx="95" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_117">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_115" height="56" width="28" y="342" x="85" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_116" height="25" width="13" y="357" x="72" fill="#f2efef"/>
                                </g>
                                <g id="svg_120">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_118" cy="370" cx="96" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_119" cy="370" cx="96" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 145.5 303)" id="svg_123">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_121" height="56" width="28" y="275" x="138" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_122" height="25" width="13" y="290" x="125" fill="#f2efef"/>
                                </g>
                                <g id="svg_126">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_124" cy="303" cx="142" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_125" cy="303" cx="142" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 146.5 370)" id="svg_129">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_127" height="56" width="28" y="342" x="139" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_128" height="25" width="13" y="357" x="126" fill="#f2efef"/>
                                </g>
                                <g id="svg_132">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_130" cy="369" cx="143" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_131" cy="369" cx="143" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_135">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_133" height="56" width="28" y="409" x="84" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_134" height="25" width="13" y="424" x="71" fill="#f2efef"/>
                                </g>
                                <g id="svg_138">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_136" cy="437" cx="95" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_137" cy="437" cx="95" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 145.5 437)" id="svg_141">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_139" height="56" width="28" y="409" x="138" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_140" height="25" width="13" y="424" x="125" fill="#f2efef"/>
                                </g>
                                <g id="svg_144">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_142" cy="436" cx="142" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_143" cy="436" cx="142" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_147">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_145" height="56" width="28" y="476" x="84" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_146" height="25" width="13" y="491" x="71" fill="#f2efef"/>
                                </g>
                                <g id="svg_150">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_148" cy="504" cx="95" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_149" cy="504" cx="95" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 145.5 504)" id="svg_153">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_151" height="56" width="28" y="476" x="138" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_152" height="25" width="13" y="491" x="125" fill="#f2efef"/>
                                </g>
                                <g id="svg_156">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_154" cy="503" cx="142" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_155" cy="503" cx="142" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_159">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_157" height="56" width="28" y="275" x="242" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_158" height="25" width="13" y="290" x="229" fill="#f2efef"/>
                                </g>
                                <g id="svg_162">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_160" cy="303" cx="253" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_161" cy="303" cx="253" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_165">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_163" height="56" width="28" y="342" x="243" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_164" height="25" width="13" y="357" x="230" fill="#f2efef"/>
                                </g>
                                <g id="svg_168">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_166" cy="370" cx="254" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_167" cy="370" cx="254" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 303.5 303)" id="svg_171">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_169" height="56" width="28" y="275" x="296" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_170" height="25" width="13" y="290" x="283" fill="#f2efef"/>
                                </g>
                                <g id="svg_174">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_172" cy="303" cx="300" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_173" cy="303" cx="300" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 304.5 370)" id="svg_177">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_175" height="56" width="28" y="342" x="297" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_176" height="25" width="13" y="357" x="284" fill="#f2efef"/>
                                </g>
                                <g id="svg_180">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_178" cy="369" cx="301" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_179" cy="369" cx="301" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_183">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_181" height="56" width="28" y="409" x="242" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_182" height="25" width="13" y="424" x="229" fill="#f2efef"/>
                                </g>
                                <g id="svg_186">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_184" cy="437" cx="253" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_185" cy="437" cx="253" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 303.5 437)" id="svg_189">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_187" height="56" width="28" y="409" x="296" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_188" height="25" width="13" y="424" x="283" fill="#f2efef"/>
                                </g>
                                <g id="svg_192">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_190" cy="436" cx="300" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_191" cy="436" cx="300" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_195">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_193" height="56" width="28" y="476" x="242" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_194" height="25" width="13" y="491" x="229" fill="#f2efef"/>
                                </g>
                                <g id="svg_198">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_196" cy="504" cx="253" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_197" cy="504" cx="253" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 303.5 504)" id="svg_201">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_199" height="56" width="28" y="476" x="296" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_200" height="25" width="13" y="491" x="283" fill="#f2efef"/>
                                </g>
                                <g id="svg_204">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_202" cy="503" cx="300" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_203" cy="503" cx="300" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_207">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_205" height="56" width="28" y="279" x="405" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_206" height="25" width="13" y="294" x="392" fill="#f2efef"/>
                                </g>
                                <g id="svg_210">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_208" cy="307" cx="416" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_209" cy="307" cx="416" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_213">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_211" height="56" width="28" y="346" x="406" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_212" height="25" width="13" y="361" x="393" fill="#f2efef"/>
                                </g>
                                <g id="svg_216">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_214" cy="374" cx="417" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_215" cy="374" cx="417" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 466.5 307)" id="svg_219">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_217" height="56" width="28" y="279" x="459" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_218" height="25" width="13" y="294" x="446" fill="#f2efef"/>
                                </g>
                                <g id="svg_222">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_220" cy="307" cx="463" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_221" cy="307" cx="463" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 467.5 374)" id="svg_225">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_223" height="56" width="28" y="346" x="460" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_224" height="25" width="13" y="361" x="447" fill="#f2efef"/>
                                </g>
                                <g id="svg_228">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_226" cy="373" cx="464" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_227" cy="373" cx="464" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_231">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_229" height="56" width="28" y="413" x="405" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_230" height="25" width="13" y="428" x="392" fill="#f2efef"/>
                                </g>
                                <g id="svg_234">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_232" cy="441" cx="416" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_233" cy="441" cx="416" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 466.5 441)" id="svg_237">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_235" height="56" width="28" y="413" x="459" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_236" height="25" width="13" y="428" x="446" fill="#f2efef"/>
                                </g>
                                <g id="svg_240">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_238" cy="440" cx="463" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_239" cy="440" cx="463" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_243">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_241" height="56" width="28" y="480" x="405" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_242" height="25" width="13" y="495" x="392" fill="#f2efef"/>
                                </g>
                                <g id="svg_246">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_244" cy="508" cx="416" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_245" cy="508" cx="416" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 466.5 508)" id="svg_249">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_247" height="56" width="28" y="480" x="459" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_248" height="25" width="13" y="495" x="446" fill="#f2efef"/>
                                </g>
                                <g id="svg_252">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_250" cy="507" cx="463" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_251" cy="507" cx="463" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <line stroke="#000" id="svg_253" y2="53" x2="297.53076" y1="53" x1="17" fill="none"/>
                                <line stroke="#000" id="svg_256" y2="53" x2="509.01073" y1="53" x1="404.13953" fill="none"/>
                                <line stroke="#000" id="svg_258" y2="586.01178" x2="508.00174" y1="52.66719" x1="508.00174" fill="none"/>
                                <line stroke="#000" id="svg_259" y2="53.33386" x2="664.0137" y1="53.33386" x1="507.33507" fill="none"/>
                                <g id="svg_263">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_261" height="56" width="28" y="73.66666" x="566.6669" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_262" height="25" width="13" y="88.66666" x="553.6669" fill="#f2efef"/>
                                </g>
                                <g id="svg_266">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_264" cy="101.66666" cx="577.6669" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_265" cy="101.66666" cx="577.6669" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_269">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_267" height="56" width="28" y="140.66666" x="567.6669" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_268" height="25" width="13" y="155.66666" x="554.6669" fill="#f2efef"/>
                                </g>
                                <g id="svg_272">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_270" cy="168.66666" cx="578.6669" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_271" cy="168.66666" cx="578.6669" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 628.167 101.667)" id="svg_275">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_273" height="56" width="28" y="73.66666" x="620.6669" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_274" height="25" width="13" y="88.66666" x="607.6669" fill="#f2efef"/>
                                </g>
                                <g id="svg_278">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_276" cy="101.66666" cx="624.6669" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_277" cy="101.66666" cx="624.6669" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 629.167 168.667)" id="svg_281">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_279" height="56" width="28" y="140.66666" x="621.6669" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_280" height="25" width="13" y="155.66666" x="608.6669" fill="#f2efef"/>
                                </g>
                                <g id="svg_284">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_282" cy="167.66666" cx="625.6669" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_283" cy="167.66666" cx="625.6669" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_287">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_285" height="56" width="28" y="74.33333" x="749.33385" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_286" height="25" width="13" y="89.33333" x="736.33385" fill="#f2efef"/>
                                </g>
                                <g id="svg_290">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_288" cy="102.33333" cx="760.33385" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_289" cy="102.33333" cx="760.33385" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_293">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_291" height="56" width="28" y="141.33333" x="750.33385" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_292" height="25" width="13" y="156.33333" x="737.33385" fill="#f2efef"/>
                                </g>
                                <g id="svg_296">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_294" cy="169.33333" cx="761.33385" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_295" cy="169.33333" cx="761.33385" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 810.834 102.334)" id="svg_299">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_297" height="56" width="28" y="74.33333" x="803.33385" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_298" height="25" width="13" y="89.33333" x="790.33385" fill="#f2efef"/>
                                </g>
                                <g id="svg_302">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_300" cy="102.33333" cx="807.33385" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_301" cy="102.33333" cx="807.33385" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 811.834 169.334)" id="svg_305">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_303" height="56" width="28" y="141.33333" x="804.33385" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_304" height="25" width="13" y="156.33333" x="791.33385" fill="#f2efef"/>
                                </g>
                                <g id="svg_308">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_306" cy="168.33333" cx="808.33385" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_307" cy="168.33333" cx="808.33385" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_311">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_309" height="56" width="28" y="206.66676" x="567.6669" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_310" height="25" width="13" y="221.66676" x="554.6669" fill="#f2efef"/>
                                </g>
                                <g id="svg_314">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_312" cy="234.66676" cx="578.6669" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_313" cy="234.66676" cx="578.6669" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 629.167 234.667)" id="svg_317">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_315" height="56" width="28" y="206.66676" x="621.6669" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_316" height="25" width="13" y="221.66676" x="608.6669" fill="#f2efef"/>
                                </g>
                                <g id="svg_320">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_318" cy="233.66676" cx="625.6669" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_319" cy="233.66676" cx="625.6669" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g id="svg_323">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_321" height="56" width="28" y="206.00009" x="751.00051" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_322" height="25" width="13" y="221.00009" x="738.00051" fill="#f2efef"/>
                                </g>
                                <g id="svg_326">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_324" cy="234.00009" cx="762.00051" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_325" cy="234.00009" cx="762.00051" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <g transform="rotate(-180 812.501 234.001)" id="svg_329">
                                <rect stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_327" height="56" width="28" y="206.00009" x="805.00051" fill="#f2efef"/>
                                <rect stroke="#000" rx="2" id="svg_328" height="25" width="13" y="221.00009" x="792.00051" fill="#f2efef"/>
                                </g>
                                <g id="svg_332">
                                <ellipse stroke="#000" ry="16" rx="16" id="svg_330" cy="233.00009" cx="809.00051" fill="#ffffff"/>
                                <ellipse stroke="#000" ry="12" rx="12" id="svg_331" cy="233.00009" cx="809.00051" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                <line stroke="#000" id="svg_333" y2="284.66754" x2="860.67141" y1="284.66754" x1="508.00174" fill="none"/>
                                <line id="svg_334" y2="286.04821" x2="861.3356" y1="56.00053" x1="861.3356" stroke="#000" fill="none"/>
                                <line id="svg_335" y2="55.33386" x2="740.65209" y1="55.33386" x1="860.66894" stroke="#000" fill="none"/>
                                <line stroke="#000" id="svg_336" y2="584.00132" x2="861.57794" y1="584.00132" x1="508.00174" fill="none"/>
                                <line stroke="#000" id="svg_338" y2="584.668" x2="861.3356" y1="376.00102" x1="861.3356" fill="none"/>
                                <g id="svg_400">
                                <rect transform="rotate(-90 678.668 429.002)" stroke="#000" rx="8" filter="url(#svg_21_blur)" id="svg_347" height="236.00027" width="84.66675" y="311.00211" x="636.3346" fill="#f2efef"/>
                                <rect transform="rotate(-90 640.002 293.002)" stroke="#000" rx="2" id="svg_348" height="25" width="13" y="231.33527" x="546.0011" fill="#f2efef"/>
                                <rect transform="rotate(-90 695.335 292.336)" stroke="#000" rx="2" id="svg_387" height="25" width="13" y="230.6686" x="601.33451" fill="#f2efef"/>
                                <rect transform="rotate(-90 750.001 292.335)" stroke="#000" rx="2" id="svg_388" height="25" width="13" y="230.6686" x="656.00126" fill="#f2efef"/>
                                <rect transform="rotate(-90 802.668 292.335)" stroke="#000" rx="2" id="svg_389" height="25" width="13" y="230.6686" x="708.668" fill="#f2efef"/>
                                <rect transform="rotate(-90 639.335 389.669)" stroke="#000" rx="2" id="svg_390" height="25" width="13" y="328.00208" x="545.33443" fill="#f2efef"/>
                                <rect transform="rotate(-90 694.001 389.669)" stroke="#000" rx="2" id="svg_391" height="25" width="13" y="328.00208" x="600.00118" fill="#f2efef"/>
                                <rect transform="rotate(-90 750.002 389.002)" stroke="#000" rx="2" id="svg_392" height="25" width="13" y="327.33541" x="656.00126" fill="#f2efef"/>
                                <rect transform="rotate(-90 802.668 389.002)" stroke="#000" rx="2" id="svg_393" height="25" width="13" y="327.33541" x="708.668" fill="#f2efef"/>
                                </g>
                                <g id="svg_404">
                                <rect rx="8" id="svg_401" height="61.33343" width="61.33343" y="396.66771" x="648.00195" stroke="#000" fill="#ffffff"/>
                                <rect stroke="#000" rx="8" id="svg_403" height="53.33341" width="53.33341" y="400.66771" x="652.00196" fill="green" @click="bookingDesk($event)"/>
                                </g>
                                </g>
                            </svg>
                        </div>
                    </v-col>
                </v-row>

                <div v-if="deskDetails" class="desk-details">
                    <v-card elevation="2" class="pa-3" justify="center">
                        <v-card-title class="headline">Dettagli Scrivania</v-card-title>
                        <v-card-text>
                        <v-list>
                            <v-list-item>
                                <v-list-item-content>
                                <v-list-item-title><strong>Nome:</strong> {{ deskDetails.name }}</v-list-item-title>
                            </v-list-item-content>
                            </v-list-item>
                            <v-list-item>
                            <v-list-item-content>
                                <v-list-item-title><strong>Tipo:</strong> {{ deskDetails.type }}</v-list-item-title>
                            </v-list-item-content>
                            </v-list-item>
                            <v-list-item>
                            <v-list-item-content>
                                <v-list-item-title><strong>Equipaggiamento:</strong> {{ deskDetails.equipment }}</v-list-item-title>
                            </v-list-item-content>
                            </v-list-item>
                            <v-list-item>
                            <v-list-item-content>
                                <v-list-item-title><strong>Piano:</strong> {{ deskDetails.floor }}</v-list-item-title>
                            </v-list-item-content>
                            </v-list-item>
                            <v-list-item>
                            <v-list-item-content>
                                <v-list-item-title><strong>Prezzo per Ora:</strong> {{ deskDetails.pricePerH }}€</v-list-item-title>
                            </v-list-item-content>
                            </v-list-item>
                        </v-list>
                        </v-card-text>
                        <v-card-actions>
                            <v-btn color="primary" @click="createBooking">Prenota</v-btn>
                            <v-alert v-model="alertVisible" :type="alertType" :value="true" dismissible>
                                {{ alertText }}
                            </v-alert>
                        </v-card-actions>
                    </v-card>
                </div>

            </v-col>
        </v-row>

    </v-container>

</template>
  


<script>
export default {

    data(){
        return {
            isSvgVisible: false,
            booking:{
                startDate: null,
                endDate: null,
                status: "",
                hasPenalty: false,
                workstationId: "",
                userId: ""
            },
            deskDetails: null,

            alertVisible: false,
            alertText: "",
            alertType: "success"
        }
    },

    methods: {


        formatDate(date) {
            const year = date.getFullYear();
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');

            return `${year}-${month}-${day}`;
        },


        formatDate2(dateString) {
            const date = new Date(dateString);
            
            const year = date.getFullYear();
            const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Aggiungi 1 perché i mesi in JavaScript iniziano da 0
            const day = date.getDate().toString().padStart(2, '0');
            const hours = date.getHours().toString().padStart(2, '0');
            const minutes = date.getMinutes().toString().padStart(2, '0');
            const seconds = date.getSeconds().toString().padStart(2, '0');
        
            const isoString = `${year}-${month}-${day}T${hours}:${minutes}:${seconds}+00:00`;
            
            return isoString;
        },

        findOccupiedDesks() {
            const date = this.formatDate(this.booking.startDate);
            console.log(date);

            this.$ApiService.find_occupied_desks(date).then((occupiedDesks) => {
                console.log(occupiedDesks)

                document.querySelectorAll('[data-id]').forEach(desk => {
                    desk.setAttribute('fill', 'green');
                });

                occupiedDesks.forEach(id => {
                    const deskElement = document.querySelector(`[data-id="${id}"]`);
                    if (deskElement) {
                        deskElement.setAttribute('fill', 'red');
                        deskElement.style.pointerEvents = 'none';
                    }
                });
            });

            this.isSvgVisible = true;
        },


        bookingDesk(event) {
            
            const workStationId = event.target.getAttribute('data-id');

            this.$ApiService.find_desk_by_id(workStationId).then((res) => {
                console.log(res.data);
                this.deskDetails = res.data;
                this.booking.workstationId = workStationId;
            });
            
        },


        createBooking(){
            console.log(this.booking.startDate)
            this.booking.startDate = this.formatDate2(this.booking.startDate)
            console.log(this.booking.startDate)
            this.booking.endDate = this.booking.startDate
            this.booking.status = "active"
            this.booking.userId = "4c6c4c49-a716-4f88-b940-701c6b5faecd"

            this.$ApiService.create_booking(this.booking).then((res) => {
                console.log(res)
            });

            this.$ApiService.create_booking(this.booking).then((res) => {
                console.log(res)
                this.alertVisible = true;
                this.alertText = "Registration was successful!"
            })
            .catch((error) => {
                this.alertType = "error";
                this.alertText = "Something went wrong try again!"
            })

        }        

    }
}
</script>
