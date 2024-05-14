import os.path;

#Add impl
class SSTFSetting:
    def __init__(self, Name: str, Data: str):
        self.name = Name
        self.data = Data

class SSTF:
    @staticmethod
    def writesettings(path, settings):
        fp = open(path, "w")
        for i in settings:
            if(type(i) is SSTFSetting):
                fp.write(i.name+"│"+i.data)
                fp.write(os.linesep)
        fp.close()

    @staticmethod
    def parsesettings(path):
        retlist = []
        if not os.path.isfile(path):
            return
        file = open(path, "r")
        lines = file.readlines()
        for line in lines:
            if not line.__contains__("│"):
                continue
            if line.index("│") != line.rindex("│"):
                continue
            nam = line[0:line.index("│")]
            dat = line[line.index("│")+1:line.__len__()]
            dat = dat.strip(os.linesep)
            setting = SSTFSetting(nam, dat)
            retlist.append(setting)
        return retlist
        #Finish later
