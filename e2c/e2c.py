# 源码文件名称:e2c.py
# 创建日期: 2023-12-14
# 功能描述: 将excel转为csv，并进行数据清洗：去空；去重；替换特殊字符；排序
# 输入参数: input_folder -- excel格式
# 输出参数: output_folder -- csv格式
# 用法: from e2c.e2c import excel_to_csv

import pandas as pd
import os
import re

def clean_text(text):
    # 只保留中文字符、字母和数字，其他字符替换为空
    cleaned_text = re.sub(r'[^a-zA-Z0-9\u4e00-\u9fa5,.\?!，。\？！]', '', str(text))
    return cleaned_text
def excel_to_csv(input_folder,output_file):
    pwd = os.path.dirname(os.path.realpath(__file__))
    file = os.path.join(pwd,input_folder)

    sheet_names = pd.ExcelFile(file).sheet_names
    # 创建一个空的DataFrame来存储合并后的数据
    merged_data = pd.DataFrame()
    #打印日志变量
    duplicated_data = pd.DataFrame()
    cleaned_data = pd.DataFrame()
    # 遍历所有Sheet页，合并数据并添加一列表示原始Sheet页的名称
    for sheet_name in sheet_names:
        # 读取每个Sheet页的数据
        sheet_data = pd.read_excel(file, sheet_name)
        # 在数据中添加一列，用于存储原始Sheet页的名称
        sheet_data['Sheet_Name'] = sheet_name
        # 将当前Sheet页的数据追加到合并后的DataFrame中
        merged_data = merged_data._append(sheet_data, ignore_index=True)

    cleaned_data = cleaned_data._append(merged_data[merged_data[merged_data.columns[0]].isna() | merged_data[merged_data.columns[4]].isna()])
    #print(cleaned_data)
    merged_data = merged_data.dropna(subset=[merged_data.columns[0], merged_data.columns[4]])
    duplicated_data = merged_data[merged_data.duplicated()]
    #print(duplicated_data)
    merged_data = merged_data.drop_duplicates()
    merged_data[merged_data.columns[4]] = merged_data[merged_data.columns[4]].apply(clean_text)
    merged_data = merged_data.sort_values(by=[merged_data.columns[5],merged_data.columns[0],merged_data.columns[2]])
    merged_data = merged_data.reset_index(drop=True)
    merged_data.to_csv(output_file,index=False,encoding='utf-8-sig')

excel_to_csv('yq_gzd.xlsx', 'yq.csv')

