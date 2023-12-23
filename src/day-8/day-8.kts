import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


val data = mutableListOf(
        "LRRLRRLRRRLRLLRRRLLRRRLRLRRRLRLRRLRRRLRRRLRLRRRLRRRLRRLRRRLLLRLRRRLRRRLRRRLRLRLRRLLRRRLRLLRLRRRLRRLLRLRLRRLRRRLRRLLRLRRRLLRRLRRRLRLRRLLRRRLRRLLRRLRRRLRLRRRLRRLRRRLRRRLRRLRRRLRLRRLRRRLRRRLRRLLRLRRLRRLRRRLRLLLRRRLLRRRLRLRRRLRLRRLRRRLLLRLRRRLRLRRLRRRLRRRLRRLRLRLRRRR",
        "",
        "PBN = (JRP, RVT)",
        "XRS = (CRH, SXK)",
        "MLN = (STJ, MGB)",
        "XGX = (XGM, GKP)",
        "VDC = (DBF, FVR)",
        "NBC = (QQJ, XKG)",
        "KRC = (QVQ, RJL)",
        "LCX = (CSN, DBP)",
        "MSX = (LVF, PPD)",
        "MMF = (GBX, LCX)",
        "BXQ = (MPS, GLM)",
        "GTL = (VSF, DVZ)",
        "SVR = (FKK, FJN)",
        "HJJ = (XPS, HVS)",
        "MHL = (HTN, HTG)",
        "LGQ = (NJT, NML)",
        "KFR = (TXK, DHG)",
        "MXQ = (DGJ, MKP)",
        "XST = (MGP, QSK)",
        "BNJ = (NBC, DFB)",
        "JLK = (RJS, XDM)",
        "BKS = (SLF, PLK)",
        "LCV = (RVB, CJV)",
        "GJL = (FRB, QNK)",
        "FPD = (SFN, MLS)",
        "HMJ = (MXK, CRR)",
        "SQF = (MCC, QXB)",
        "CGC = (SCS, FFB)",
        "CTP = (RJQ, BSV)",
        "QCF = (JCB, TKP)",
        "LBJ = (XKV, DQM)",
        "RMC = (LLD, TTP)",
        "LHK = (CDB, TSV)",
        "HPC = (QLX, QPQ)",
        "VXC = (PLF, SSV)",
        "RDT = (GJL, TTN)",
        "SDB = (MVL, FRF)",
        "HRT = (DVS, BHX)",
        "MHR = (VXR, VQN)",
        "RPT = (BLP, DDP)",
        "JFF = (DCJ, XST)",
        "NDL = (NJQ, BNC)",
        "FXL = (MSC, JSR)",
        "DVZ = (JVJ, KLP)",
        "XGD = (RFH, DTL)",
        "MPR = (QNN, CKH)",
        "SBB = (VTK, TSS)",
        "DRJ = (GHM, NFK)",
        "LXL = (SSJ, CQR)",
        "BTN = (MXN, HLF)",
        "SMX = (VFT, NKJ)",
        "RJQ = (STS, GLR)",
        "QLV = (PNP, TDK)",
        "QMJ = (MKP, DGJ)",
        "XDM = (SFB, JLN)",
        "LFX = (CDM, LMX)",
        "QTX = (RVB, CJV)",
        "CPX = (TTH, CBK)",
        "TFF = (CLG, LGN)",
        "JDC = (BHH, HFJ)",
        "TXV = (JVF, FXS)",
        "LBP = (PRS, PRS)",
        "LND = (QLV, TLK)",
        "RXR = (BVT, QKX)",
        "RSR = (GFN, GVL)",
        "FLG = (RMC, LTD)",
        "TPB = (PJP, QCL)",
        "KQH = (LRG, MRL)",
        "VVN = (KPP, HJH)",
        "NTR = (RPQ, GFG)",
        "QDK = (JFC, XGN)",
        "VBA = (KLP, JVJ)",
        "XSP = (KVF, VJV)",
        "HVB = (CQR, SSJ)",
        "XBN = (GBD, TCS)",
        "RFH = (FGN, NSP)",
        "HTN = (NBH, SMP)",
        "PSD = (FLS, KCJ)",
        "MGB = (SDF, MHR)",
        "NXT = (HKQ, MLD)",
        "MHC = (JSR, MSC)",
        "KPP = (HHV, FNC)",
        "TSS = (RBD, LGQ)",
        "BMS = (NLL, NFJ)",
        "BKV = (NLK, BBH)",
        "SDF = (VXR, VQN)",
        "GLR = (LFX, XXL)",
        "NBN = (SXB, DLD)",
        "CVV = (KGX, DTQ)",
        "VKG = (VNG, PTR)",
        "FJR = (KNQ, XGX)",
        "RJN = (PGR, QCV)",
        "CNJ = (LSV, BVN)",
        "DTC = (RXR, MHX)",
        "FVB = (BSM, BGR)",
        "SFG = (KSP, GXM)",
        "PLN = (PDV, XJD)",
        "THL = (KRC, HXH)",
        "PNP = (JQQ, SMV)",
        "STS = (LFX, XXL)",
        "NNC = (MPG, RVG)",
        "NJT = (JGJ, CGH)",
        "SCS = (NSR, DXX)",
        "GSF = (LHP, FTK)",
        "GBD = (DSN, HRT)",
        "JVF = (HPT, QHF)",
        "PSV = (NJC, CPH)",
        "JLP = (FGD, BFF)",
        "CRR = (PRB, HCH)",
        "FVJ = (VBP, BBQ)",
        "MVL = (CGS, BFN)",
        "JXK = (SFN, MLS)",
        "SSV = (DJB, TRK)",
        "LXQ = (LSB, SPC)",
        "LHS = (SSV, PLF)",
        "DDK = (QXR, QCF)",
        "XKG = (BTS, KFL)",
        "TSM = (QCV, PGR)",
        "CPT = (TMH, DFQ)",
        "DGJ = (MMD, PJN)",
        "MQP = (CFG, LRB)",
        "RQM = (JTK, PLN)",
        "PLK = (KQF, HSZ)",
        "HVC = (NLK, BBH)",
        "LSB = (NDT, TPG)",
        "RMN = (SKJ, PBH)",
        "QXL = (JNC, XMH)",
        "TDD = (BSX, GBR)",
        "BGP = (LBP, LBP)",
        "TVA = (FBB, VBN)",
        "VSB = (VNG, PTR)",
        "XLH = (HMX, FJX)",
        "NVQ = (RPQ, RPQ)",
        "FMN = (BSV, RJQ)",
        "XMC = (QCD, RJK)",
        "HPT = (HGL, GNS)",
        "CMS = (KVT, FQR)",
        "QHF = (HGL, GNS)",
        "QNT = (BPT, FLG)",
        "KVD = (SLL, GGK)",
        "DMK = (HKT, CGD)",
        "CRM = (BTR, GHV)",
        "TPJ = (GCT, DQD)",
        "KFL = (RDG, VDC)",
        "QCR = (DNK, THD)",
        "CLV = (MTS, PBN)",
        "ZZZ = (DPM, TCG)",
        "QHN = (TNC, KVD)",
        "NRP = (SQL, PMR)",
        "JNH = (TKD, LQF)",
        "VPR = (PKX, CTD)",
        "LVD = (MQC, QKJ)",
        "XRM = (BRM, MPR)",
        "FFB = (NSR, DXX)",
        "MGR = (MNB, TSP)",
        "FMK = (THR, VGR)",
        "KVT = (GSN, RXF)",
        "VMS = (LPM, VJP)",
        "DVA = (MHC, FXL)",
        "MXS = (RJS, XDM)",
        "KXV = (KPF, NKX)",
        "BBH = (XSP, GMD)",
        "PKX = (XHC, CPV)",
        "NSL = (SMX, DNM)",
        "DXV = (LPM, VJP)",
        "CCC = (LCX, GBX)",
        "JVS = (HJJ, FLF)",
        "HPM = (QSF, CPT)",
        "FVS = (XXR, CGF)",
        "PPB = (XGX, KNQ)",
        "XPF = (SMX, DNM)",
        "HLF = (NQJ, CVV)",
        "TJS = (LXQ, VFB)",
        "FJK = (FGD, BFF)",
        "QGQ = (FVJ, BDV)",
        "GRR = (JKM, JLH)",
        "GHV = (XGD, QBH)",
        "RVT = (PVB, PCS)",
        "TBL = (VGK, KTT)",
        "SNR = (GCT, DQD)",
        "XSK = (BLL, MNF)",
        "VDS = (CCC, MMF)",
        "RVG = (XSK, XSJ)",
        "TKP = (MLN, KRX)",
        "SRP = (QLV, TLK)",
        "FXQ = (LVD, RHD)",
        "LVC = (LCT, LKS)",
        "HNC = (MQP, KTG)",
        "DFP = (NMX, JFX)",
        "JVT = (SNX, KQK)",
        "JSR = (KXN, LXS)",
        "HCV = (MFD, KJX)",
        "RBJ = (JKT, HLZ)",
        "HFC = (JFX, NMX)",
        "XQG = (PGD, XFT)",
        "FRB = (HCP, LVJ)",
        "LQF = (QCR, XTL)",
        "VGR = (RMN, LNQ)",
        "MMN = (HDR, JTL)",
        "PJN = (VNM, KHQ)",
        "TRK = (NSL, XPF)",
        "TVG = (CJH, THL)",
        "DMH = (GPM, MSX)",
        "VTK = (RBD, LGQ)",
        "TMH = (BSQ, CMT)",
        "JPN = (LTH, LST)",
        "SFB = (NKN, RFM)",
        "CGD = (VBH, ZZZ)",
        "VNM = (LMT, VRQ)",
        "NNT = (HHX, NTF)",
        "VFQ = (HMJ, VFV)",
        "SQL = (BCQ, NBN)",
        "QKJ = (GHN, HCK)",
        "MMR = (KMF, HPM)",
        "PFS = (HVL, CTG)",
        "FVD = (LBJ, DVM)",
        "SLF = (KQF, KQF)",
        "BBQ = (RSV, LHK)",
        "TCG = (NGG, LLS)",
        "BLR = (PTL, MSR)",
        "CVH = (MVL, FRF)",
        "GHN = (CKF, HTV)",
        "XQC = (QXR, QCF)",
        "GCT = (BGP, BGP)",
        "XMJ = (BVN, LSV)",
        "LBH = (VQX, SBC)",
        "BNC = (LNH, CRM)",
        "THP = (KBC, MHH)",
        "VRQ = (VVN, THX)",
        "BFN = (SGD, QXL)",
        "TFH = (TDD, HGV)",
        "MLS = (PRJ, JPQ)",
        "XDQ = (BXQ, RQR)",
        "DJS = (MTS, PBN)",
        "SSK = (HKQ, MLD)",
        "VBH = (TCG, DPM)",
        "NNJ = (JHS, KFR)",
        "JRP = (PCS, PVB)",
        "FDK = (SRP, LND)",
        "FCL = (HPC, NGH)",
        "BGR = (MFV, CXJ)",
        "HDR = (FXQ, KSN)",
        "PPD = (PKL, KTR)",
        "BLP = (DKJ, DKJ)",
        "BCX = (PTL, MSR)",
        "FLF = (HVS, XPS)",
        "JPQ = (DMH, RTN)",
        "GNS = (SLT, HHM)",
        "GVH = (PRS, XKZ)",
        "PDV = (VDK, VDK)",
        "PQF = (BQN, CHK)",
        "SCX = (NLL, NFJ)",
        "JNC = (VNN, FVD)",
        "THR = (LNQ, RMN)",
        "VBP = (RSV, LHK)",
        "MTS = (JRP, RVT)",
        "NKG = (RPG, TFH)",
        "LXK = (JXM, GSF)",
        "TXK = (RJC, QNT)",
        "SMV = (FTJ, GPD)",
        "DCP = (FFB, SCS)",
        "CJK = (FJR, PPB)",
        "LGN = (NLF, MTJ)",
        "CKF = (SMF, BSH)",
        "TRP = (TNC, KVD)",
        "TPG = (DJM, XMK)",
        "VJC = (JSX, NBT)",
        "LFK = (RXR, MHX)",
        "SBX = (FJN, FKK)",
        "FBB = (BMS, SCX)",
        "BHX = (SDN, HTF)",
        "CSN = (NHV, QVH)",
        "QQJ = (BTS, KFL)",
        "HFJ = (MMR, KCR)",
        "SVS = (GFN, GVL)",
        "FJL = (CJJ, GQF)",
        "DHG = (QNT, RJC)",
        "PCS = (VFG, SQF)",
        "RVB = (SBB, TND)",
        "PVB = (SQF, VFG)",
        "LPJ = (LRK, LXK)",
        "GVL = (NGL, GNV)",
        "CTJ = (QCD, RJK)",
        "PQB = (HMD, TQB)",
        "MCC = (SFV, DVV)",
        "QXX = (VBC, QGQ)",
        "RNM = (XXQ, LLT)",
        "HXB = (TRP, QHN)",
        "JFC = (TFF, DFC)",
        "SQQ = (JQL, HNC)",
        "KHQ = (LMT, VRQ)",
        "XCC = (FMK, DGK)",
        "JTL = (FXQ, KSN)",
        "NLF = (MLK, PLD)",
        "FVR = (GVK, CMS)",
        "SMF = (SNR, TPJ)",
        "NJL = (TRP, QHN)",
        "CSR = (DVP, KHX)",
        "QNJ = (THL, CJH)",
        "SBT = (XST, DCJ)",
        "RLX = (DGK, FMK)",
        "THX = (KPP, HJH)",
        "KTG = (CFG, LRB)",
        "PVC = (RQR, BXQ)",
        "DLD = (GSM, DTT)",
        "BFF = (JBH, JVT)",
        "QKX = (FQJ, SFJ)",
        "DQD = (BGP, XHS)",
        "VFB = (LSB, SPC)",
        "MSR = (VBK, JTQ)",
        "CKB = (HHX, NTF)",
        "FLS = (QNM, PQB)",
        "QSF = (TMH, DFQ)",
        "FTK = (FDK, FLQ)",
        "FJX = (JVC, GXS)",
        "BDF = (MPR, BRM)",
        "MXK = (PRB, HCH)",
        "SDQ = (FJX, HMX)",
        "JCC = (TSP, MNB)",
        "GKP = (JLK, MXS)",
        "MRL = (FVS, NLQ)",
        "DPM = (LLS, NGG)",
        "GFL = (DDJ, NKG)",
        "FBG = (RDT, MVG)",
        "KJX = (VCJ, GHB)",
        "GFC = (LLT, XXQ)",
        "LVJ = (DJS, CLV)",
        "KNQ = (XGM, GKP)",
        "NJN = (FLS, KCJ)",
        "DSS = (KPF, NKX)",
        "JVC = (FHD, BMQ)",
        "TVP = (TBL, MVX)",
        "BQN = (SHT, TPS)",
        "DFQ = (CMT, BSQ)",
        "GXS = (FHD, BMQ)",
        "LRK = (JXM, GSF)",
        "KGX = (NDL, XDN)",
        "VTD = (SXK, CRH)",
        "KHH = (HKT, HKT)",
        "NTF = (NVQ, NTR)",
        "KRX = (STJ, MGB)",
        "SPC = (NDT, TPG)",
        "DJM = (JPN, TNG)",
        "THD = (VFQ, GCC)",
        "CLG = (NLF, MTJ)",
        "NKN = (SRT, VHH)",
        "GGK = (RLX, XCC)",
        "HHV = (LCV, QTX)",
        "LRG = (NLQ, FVS)",
        "GNG = (BDF, XRM)",
        "FNC = (QTX, LCV)",
        "MVV = (QDK, LNK)",
        "QTH = (MSH, XDC)",
        "MKP = (MMD, PJN)",
        "VBC = (FVJ, BDV)",
        "JKT = (FJL, BNB)",
        "MFV = (SVS, RSR)",
        "HKX = (CPH, NJC)",
        "HCH = (BKV, HVC)",
        "TLK = (TDK, PNP)",
        "DNK = (GCC, VFQ)",
        "VXR = (NJV, KLH)",
        "MPG = (XSJ, XSK)",
        "JLN = (NKN, RFM)",
        "LTH = (QQS, TXV)",
        "FLQ = (LND, SRP)",
        "SXN = (MHL, MHL)",
        "CFG = (BXX, NRP)",
        "NDT = (XMK, DJM)",
        "TTP = (VPX, SXR)",
        "QXR = (TKP, JCB)",
        "TDK = (JQQ, SMV)",
        "KXN = (NXH, BNJ)",
        "VPA = (HTN, HTG)",
        "VHH = (DSG, NCN)",
        "AAA = (TCG, DPM)",
        "GHM = (NNC, VKX)",
        "GPM = (PPD, LVF)",
        "CGH = (LVC, BMH)",
        "NJQ = (LNH, CRM)",
        "DKB = (HPC, NGH)",
        "VKB = (KBC, MHH)",
        "CKH = (LXL, HVB)",
        "TSP = (BNN, CBJ)",
        "FKS = (JTK, PLN)",
        "NGH = (QLX, QPQ)",
        "MFT = (QMR, CDQ)",
        "BSV = (GLR, STS)",
        "RQR = (GLM, MPS)",
        "DDP = (DKJ, GTL)",
        "SQP = (MVG, RDT)",
        "LMX = (PLH, DDV)",
        "JCB = (KRX, MLN)",
        "HCP = (DJS, CLV)",
        "KQF = (MHC, FXL)",
        "NML = (CGH, JGJ)",
        "DRG = (KFR, JHS)",
        "NLQ = (XXR, CGF)",
        "BNB = (CJJ, GQF)",
        "MNF = (SQH, HCV)",
        "HTF = (FHL, FTC)",
        "JKM = (QNJ, TVG)",
        "CDM = (DDV, PLH)",
        "JHS = (TXK, DHG)",
        "BDK = (TTH, CBK)",
        "QVQ = (XJC, MFT)",
        "MMD = (VNM, KHQ)",
        "RFC = (HVL, CTG)",
        "BNN = (FGS, SFG)",
        "QCL = (GFL, KKH)",
        "KSN = (RHD, LVD)",
        "XMN = (XQC, DDK)",
        "NJC = (TSM, RJN)",
        "SQH = (MFD, KJX)",
        "QCD = (KHH, KHH)",
        "KTT = (PSV, HKX)",
        "SRT = (NCN, DSG)",
        "MHX = (QKX, BVT)",
        "CBJ = (FGS, SFG)",
        "TTH = (TMF, FPL)",
        "DBF = (GVK, CMS)",
        "DNM = (NKJ, VFT)",
        "XGM = (MXS, JLK)",
        "NLL = (FMJ, CPM)",
        "HMD = (FKS, RQM)",
        "PXT = (JTL, HDR)",
        "GHB = (GGQ, MVV)",
        "QCV = (VMS, DXV)",
        "SBC = (FJK, JLP)",
        "SGD = (XMH, JNC)",
        "JQQ = (FTJ, GPD)",
        "JLH = (TVG, QNJ)",
        "KPF = (XDQ, PVC)",
        "VQX = (JLP, FJK)",
        "VDK = (SLF, SLF)",
        "PLH = (SBX, SVR)",
        "QNN = (LXL, HVB)",
        "SXB = (GSM, DTT)",
        "JQL = (MQP, KTG)",
        "JXM = (LHP, FTK)",
        "QXB = (SFV, DVV)",
        "VBK = (BDK, CPX)",
        "BVT = (FQJ, SFJ)",
        "BHK = (KXV, DSS)",
        "CJJ = (MXQ, QMJ)",
        "XTL = (DNK, THD)",
        "BDV = (BBQ, VBP)",
        "CPH = (RJN, TSM)",
        "DTA = (FJL, BNB)",
        "TQB = (FKS, RQM)",
        "KMR = (NNT, CKB)",
        "LRB = (BXX, NRP)",
        "MVG = (TTN, GJL)",
        "XXL = (LMX, CDM)",
        "LMT = (VVN, THX)",
        "QMR = (PFS, RFC)",
        "BKD = (DVP, KHX)",
        "QNK = (LVJ, HCP)",
        "DGK = (THR, VGR)",
        "XHC = (FBG, SQP)",
        "TNC = (GGK, SLL)",
        "VJP = (GGM, HJD)",
        "VQN = (NJV, KLH)",
        "LLD = (SXR, VPX)",
        "XDC = (CST, BHK)",
        "KQK = (CNJ, XMJ)",
        "XJC = (QMR, CDQ)",
        "KKH = (NKG, DDJ)",
        "FRF = (CGS, BFN)",
        "FGS = (KSP, GXM)",
        "GSB = (GBK, VDS)",
        "NKX = (XDQ, PVC)",
        "SDN = (FHL, FTC)",
        "BVN = (CTP, FMN)",
        "QSK = (DCB, KSC)",
        "CTD = (CPV, XHC)",
        "NBH = (XBN, NVB)",
        "HRS = (LGD, VSK)",
        "JTQ = (CPX, BDK)",
        "CMT = (HXP, KMR)",
        "DKJ = (VSF, VSF)",
        "VNG = (KQH, PCC)",
        "CPV = (SQP, FBG)",
        "NBT = (HJK, GNG)",
        "VCJ = (GGQ, MVV)",
        "LNQ = (SKJ, PBH)",
        "DFB = (QQJ, XKG)",
        "RJC = (FLG, BPT)",
        "SFV = (XMN, QJS)",
        "RHD = (MQC, QKJ)",
        "QQS = (JVF, FXS)",
        "NHV = (BMN, GRR)",
        "NGG = (SQQ, CQQ)",
        "XGN = (TFF, DFC)",
        "HCK = (CKF, HTV)",
        "SNX = (CNJ, XMJ)",
        "PRB = (HVC, BKV)",
        "SXR = (SSK, NXT)",
        "GGQ = (LNK, QDK)",
        "DCJ = (QSK, MGP)",
        "MFD = (GHB, VCJ)",
        "GLM = (XQG, PDF)",
        "TND = (TSS, VTK)",
        "JFB = (BLP, BLP)",
        "KSC = (VKB, THP)",
        "CXJ = (SVS, RSR)",
        "VMT = (MXN, HLF)",
        "NSP = (HCC, TVP)",
        "BSK = (PKX, CTD)",
        "MPS = (XQG, PDF)",
        "NJV = (CJK, MKD)",
        "CGF = (CGC, DCP)",
        "GMD = (VJV, KVF)",
        "CRL = (PJP, QCL)",
        "MGP = (KSC, DCB)",
        "CQQ = (JQL, HNC)",
        "GGZ = (HTG, HTN)",
        "TNG = (LST, LTH)",
        "CST = (KXV, DSS)",
        "VFV = (CRR, MXK)",
        "RPG = (TDD, HGV)",
        "CQR = (DRG, NNJ)",
        "KSP = (DPT, GSB)",
        "VKX = (MPG, RVG)",
        "FMJ = (PQF, CVL)",
        "SLL = (RLX, XCC)",
        "HLZ = (BNB, FJL)",
        "GXM = (GSB, DPT)",
        "BSH = (SNR, TPJ)",
        "HGL = (HHM, SLT)",
        "QVH = (GRR, BMN)",
        "BTR = (XGD, QBH)",
        "RBD = (NML, NJT)",
        "HXP = (NNT, CKB)",
        "BMP = (JCC, MGR)",
        "KLP = (FGF, TJS)",
        "NRK = (VBC, QGQ)",
        "XMK = (TNG, JPN)",
        "PBH = (GCL, LPJ)",
        "NSR = (JFB, RPT)",
        "SHT = (JVS, HKN)",
        "NVB = (GBD, TCS)",
        "MLK = (QTH, GTT)",
        "FTC = (BSK, VPR)",
        "PCC = (MRL, LRG)",
        "PTL = (VBK, JTQ)",
        "NKJ = (FCL, DKB)",
        "GFN = (NGL, NGL)",
        "MQC = (GHN, HCK)",
        "KMF = (QSF, CPT)",
        "DDV = (SBX, SVR)",
        "KHX = (BLR, BCX)",
        "DVP = (BCX, BLR)",
        "KBC = (MCG, FKC)",
        "FJN = (JFF, SBT)",
        "FKK = (JFF, SBT)",
        "DDJ = (RPG, TFH)",
        "JVM = (JCC, MGR)",
        "PJH = (JKT, JKT)",
        "GNV = (SXN, KFC)",
        "LKS = (CVH, SDB)",
        "JBH = (SNX, KQK)",
        "XSJ = (MNF, BLL)",
        "MNB = (BNN, CBJ)",
        "FQR = (RXF, GSN)",
        "NCN = (LHS, VXC)",
        "XXR = (DCP, CGC)",
        "TSQ = (BSM, BGR)",
        "VSK = (BMP, JVM)",
        "DTQ = (XDN, NDL)",
        "LSV = (FMN, CTP)",
        "FHD = (NJL, HXB)",
        "MTJ = (PLD, MLK)",
        "LBC = (CSR, BKD)",
        "NGL = (SXN, SXN)",
        "DSN = (BHX, DVS)",
        "LLT = (VJC, DSF)",
        "XJD = (VDK, BKS)",
        "HGV = (BSX, GBR)",
        "HKT = (VBH, VBH)",
        "FGN = (HCC, TVP)",
        "HHG = (CSR, BKD)",
        "TSV = (HFC, DFP)",
        "FBK = (TSQ, FVB)",
        "FGD = (JVT, JBH)",
        "CGS = (SGD, QXL)",
        "SLT = (HHG, LBC)",
        "HMX = (JVC, GXS)",
        "BVG = (VSK, LGD)",
        "GSM = (FPD, JXK)",
        "RJK = (KHH, DMK)",
        "LNH = (GHV, BTR)",
        "PLF = (DJB, TRK)",
        "PMR = (NBN, BCQ)",
        "CDB = (HFC, DFP)",
        "HSZ = (FXL, MHC)",
        "FHL = (BSK, VPR)",
        "JTK = (PDV, XJD)",
        "MVX = (KTT, VGK)",
        "GPD = (JNH, JDV)",
        "JGJ = (BMH, LVC)",
        "JVJ = (TJS, FGF)",
        "CVL = (BQN, CHK)",
        "SSJ = (NNJ, DRG)",
        "JDV = (TKD, LQF)",
        "JFX = (PSD, NJN)",
        "RXF = (TKS, LBH)",
        "CDQ = (PFS, RFC)",
        "FKC = (NRK, QXX)",
        "BMH = (LKS, LCT)",
        "MSH = (BHK, CST)",
        "LLS = (CQQ, SQQ)",
        "LST = (QQS, TXV)",
        "GSN = (LBH, TKS)",
        "RSV = (TSV, CDB)",
        "DJB = (XPF, NSL)",
        "GBR = (DTC, LFK)",
        "MKD = (FJR, PPB)",
        "HHX = (NVQ, NTR)",
        "LTD = (TTP, LLD)",
        "HKQ = (CTJ, XMC)",
        "KTR = (RNM, GFC)",
        "NMX = (NJN, PSD)",
        "FFP = (HFJ, BHH)",
        "BTS = (VDC, RDG)",
        "GBK = (MMF, CCC)",
        "MCG = (NRK, QXX)",
        "LCT = (CVH, SDB)",
        "CTG = (XRS, VTD)",
        "VJV = (BTN, VMT)",
        "HHM = (LBC, HHG)",
        "NQJ = (DTQ, KGX)",
        "MLD = (CTJ, XMC)",
        "BSM = (CXJ, MFV)",
        "GQF = (QMJ, MXQ)",
        "DXX = (JFB, RPT)",
        "BRM = (CKH, QNN)",
        "TKS = (SBC, VQX)",
        "KCR = (KMF, HPM)",
        "FGF = (VFB, LXQ)",
        "GFG = (PJH, RBJ)",
        "XXQ = (DSF, VJC)",
        "TKD = (QCR, XTL)",
        "KVF = (BTN, VMT)",
        "KCJ = (PQB, QNM)",
        "VSF = (KLP, JVJ)",
        "SKJ = (GCL, LPJ)",
        "DBP = (QVH, NHV)",
        "JSX = (GNG, HJK)",
        "DSF = (JSX, NBT)",
        "PLD = (QTH, GTT)",
        "CJT = (NFK, GHM)",
        "NFK = (VKX, NNC)",
        "HTV = (SMF, BSH)",
        "DTL = (NSP, FGN)",
        "QPQ = (TPB, CRL)",
        "DVM = (XKV, DQM)",
        "SXK = (VSB, VKG)",
        "PJP = (GFL, KKH)",
        "DSG = (LHS, VXC)",
        "CPM = (CVL, PQF)",
        "STJ = (SDF, MHR)",
        "XDN = (NJQ, BNC)",
        "PRS = (FBB, VBN)",
        "CJH = (KRC, HXH)",
        "BHH = (KCR, MMR)",
        "GCC = (VFV, HMJ)",
        "RFM = (SRT, VHH)",
        "PGR = (VMS, DXV)",
        "SMP = (NVB, XBN)",
        "PKL = (GFC, RNM)",
        "PDF = (PGD, XFT)",
        "DPT = (VDS, GBK)",
        "BXX = (SQL, PMR)",
        "DFC = (CLG, LGN)",
        "GCL = (LRK, LXK)",
        "BMQ = (HXB, NJL)",
        "XPS = (XLH, SDQ)",
        "CHK = (TPS, SHT)",
        "DCB = (VKB, THP)",
        "PTR = (KQH, PCC)",
        "RJL = (XJC, MFT)",
        "MHH = (MCG, FKC)",
        "PRJ = (DMH, RTN)",
        "CBK = (TMF, FPL)",
        "NLK = (XSP, GMD)",
        "FPL = (HRS, BVG)",
        "HCC = (MVX, TBL)",
        "GTT = (XDC, MSH)",
        "VNN = (DVM, LBJ)",
        "PGD = (FFP, JDC)",
        "LVF = (PKL, KTR)",
        "LHP = (FLQ, FDK)",
        "GVK = (KVT, FQR)",
        "KLH = (CJK, MKD)",
        "BMN = (JKM, JLH)",
        "LXS = (BNJ, NXH)",
        "VFT = (FCL, DKB)",
        "LPM = (HJD, GGM)",
        "QLX = (CRL, TPB)",
        "FXS = (QHF, HPT)",
        "GBX = (CSN, DBP)",
        "BCQ = (DLD, SXB)",
        "HTG = (NBH, SMP)",
        "DVS = (HTF, SDN)",
        "XMH = (FVD, VNN)",
        "BLL = (HCV, SQH)",
        "QBH = (RFH, DTL)",
        "FQJ = (FBK, MMT)",
        "CRH = (VKG, VSB)",
        "MSC = (LXS, KXN)",
        "HJD = (MMN, PXT)",
        "LGD = (BMP, JVM)",
        "DVV = (XMN, QJS)",
        "NFJ = (FMJ, CPM)",
        "MXN = (NQJ, CVV)",
        "VFG = (MCC, QXB)",
        "TCS = (DSN, HRT)",
        "BSQ = (HXP, KMR)",
        "HVS = (SDQ, XLH)",
        "MMT = (FVB, TSQ)",
        "XHS = (LBP, GVH)",
        "HXH = (RJL, QVQ)",
        "DTT = (JXK, FPD)",
        "HJH = (HHV, FNC)",
        "QNM = (TQB, HMD)",
        "RPQ = (PJH, PJH)",
        "DQM = (DRJ, CJT)",
        "RJS = (JLN, SFB)",
        "NXH = (DFB, NBC)",
        "GGM = (MMN, PXT)",
        "FTJ = (JDV, JNH)",
        "BSX = (DTC, LFK)",
        "HKN = (FLF, HJJ)",
        "VGK = (HKX, PSV)",
        "VPX = (NXT, SSK)",
        "SFJ = (MMT, FBK)",
        "KFC = (MHL, GGZ)",
        "RDG = (DBF, FVR)",
        "RTN = (MSX, GPM)",
        "QJS = (XQC, DDK)",
        "XKZ = (VBN, FBB)",
        "TMF = (HRS, BVG)",
        "CJV = (TND, SBB)",
        "XFT = (JDC, FFP)",
        "TPS = (HKN, JVS)",
        "BPT = (RMC, LTD)",
        "HVL = (XRS, VTD)",
        "HJK = (XRM, BDF)",
        "XKV = (CJT, DRJ)",
        "SFN = (JPQ, PRJ)",
        "TTN = (QNK, FRB)",
        "LNK = (XGN, JFC)",
        "VBN = (BMS, SCX)"
)

val instructions = mutableMapOf<String, Instruction>()

class Instruction(directionL: String, directionR: String) {
    val directionL = directionL
    val directionR = directionR

    fun getDirection(direction: Char): String {
        if (direction == 'R') return directionR
        return directionL
    }

    override fun toString(): String {
        return "($directionL, $directionR)"
    }
}

fun lcm(number1: Double, number2: Double): Double {
    if (number1 == 0.0 || number2 == 0.0) {
        return 0.0
    }
    val absNumber1 = abs(number1)
    val absNumber2 = abs(number2)
    val absHigherNumber = max(absNumber1, absNumber2)
    val absLowerNumber = min(absNumber1, absNumber2)
    var lcm = absHigherNumber
    while (lcm % absLowerNumber != 0.0) {
        lcm += absHigherNumber
    }
    return lcm
}


fun extractValues(data: List<String>) {
    data.forEach {
        val values = it.trim().replace("\\s+".toRegex(), " ").split("=").map { e -> e.trim() }
        val directionsLR = values[1].replace("(", "").replace(")", "").replace(" ", "").split(",")
        instructions[values[0]] = Instruction(directionsLR[0], directionsLR[1])
    }
}

fun main() {
    var directions = data[0].trim()
    data.removeFirst()
    data.removeFirst()
    extractValues(data)

    var steps = 0
    var currentPosition = "AAA"
    var currentInstruction = instructions[currentPosition]

    while (currentPosition != "ZZZ") {
        for (direction in directions) {
            if (currentInstruction != null) {
                currentPosition = currentInstruction.getDirection(direction)
                currentInstruction = instructions[currentPosition]
                steps += 1
                if (currentPosition == "ZZZ") break
            }
        }
    }

    println("steps (part-1): $steps") // 11309

    val stepsList = mutableListOf<Int>()
    val startingPositions = instructions.keys.filter { it.endsWith("A") }

    for (position in startingPositions) {
        currentPosition = position
        currentInstruction = instructions[currentPosition]
        steps = 0
        while (!currentPosition.endsWith("Z")) {
            for (direction in directions) {
                steps += 1
                if (currentInstruction != null) {
                    currentPosition = currentInstruction.getDirection(direction)
                    currentInstruction = instructions[currentPosition]
                }

                if (currentPosition.endsWith("Z")) break
            }
        }
        stepsList.add(steps)
    }

    var steps2 = stepsList[0].toDouble()
    for (i in 1..<stepsList.size) {
        steps2 = lcm(steps2, stepsList[i].toDouble())
    }

    println("steps (part-2): %.0f".format(steps2)) // 13740108158591
}

main()